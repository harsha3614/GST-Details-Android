package com.example.signuploginrealtime;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GstInvoiceMakerActivity extends AppCompatActivity {

    private EditText editTextCustomerName, editTextCustomerAddress, editTextCustomerPhone;
    private EditText editTextProductName, editTextQuantity, editTextPricePerUnit, editTextGSTRate;
    private TextView textViewTotalAmount;
    private TableLayout tableLayoutItems;
    private List<Item> itemList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst_invoice_maker);

        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextCustomerAddress = findViewById(R.id.editTextCustomerAddress);
        editTextCustomerPhone = findViewById(R.id.editTextCustomerPhone);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPricePerUnit = findViewById(R.id.editTextPricePerUnit);
        editTextGSTRate = findViewById(R.id.editTextGSTRate);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        tableLayoutItems = findViewById(R.id.tableLayoutItems);
        editTextGSTRate = findViewById(R.id.editTextGSTRate);
        tableLayoutItems = findViewById(R.id.tableLayoutItems);
        itemList = new ArrayList<>();
    }

    public void addItem(View view) {
        String productName = editTextProductName.getText().toString().trim();
        String quantityStr = editTextQuantity.getText().toString().trim();
        String pricePerUnitStr = editTextPricePerUnit.getText().toString().trim();
        String gstRateStr = editTextGSTRate.getText().toString().trim();

        if (productName.isEmpty() || quantityStr.isEmpty() || pricePerUnitStr.isEmpty() || gstRateStr.isEmpty()) {
            Toast.makeText(this, "Please fill all item fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);
        double pricePerUnit = Double.parseDouble(pricePerUnitStr);
        double gstRate = Double.parseDouble(gstRateStr);

        Item item = new Item(productName, quantity, pricePerUnit, gstRate);
        itemList.add(item);

        // Clear item fields after adding an item
        editTextProductName.getText().clear();
        editTextQuantity.getText().clear();
        editTextPricePerUnit.getText().clear();
        editTextGSTRate.getText().clear();

        // Update the display of item details
        updateItemDetailsDisplay();
    }

    private void updateItemDetailsDisplay() {
        tableLayoutItems.removeAllViews(); // Clear existing views

        // Table Headings
        TableRow headingRow = new TableRow(this);
        addHeadingToTableRow(headingRow, "Product Name");
        addHeadingToTableRow(headingRow, "Quantity");
        addHeadingToTableRow(headingRow, "Price per Unit");
        addHeadingToTableRow(headingRow, "GST Rate (%)");
        addHeadingToTableRow(headingRow, "Total Price");
        tableLayoutItems.addView(headingRow);

        for (Item item : itemList) {
            TableRow itemRow = new TableRow(this);
            addTextToTableRow(itemRow, item.getProductName());
            addTextToTableRow(itemRow, String.valueOf(item.getQuantity()));
            addTextToTableRow(itemRow, String.format("$%.2f", item.getPricePerUnit()));
            addTextToTableRow(itemRow, String.format("%.2f%%", item.getGstRate()));
            addTextToTableRow(itemRow, String.format("$%.2f", item.getTotalPrice()));
            tableLayoutItems.addView(itemRow);
        }
    }

    private void addHeadingToTableRow(TableRow row, String heading) {
        TextView textView = new TextView(this);
        textView.setText(heading);
        textView.setPadding(8, 8, 8, 8);
        row.addView(textView);
    }

    private void addTextToTableRow(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        row.addView(textView);
    }

    public void calculateTotal(View view) {
        String customerName = editTextCustomerName.getText().toString().trim();
        String customerAddress = editTextCustomerAddress.getText().toString().trim();
        String customerPhone = editTextCustomerPhone.getText().toString().trim();

        if (customerName.isEmpty() || customerAddress.isEmpty() || customerPhone.isEmpty()) {
            Toast.makeText(this, "Please fill all customer details", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalAmount = 0;

        for (Item item : itemList) {
            totalAmount += item.getTotalPrice();
        }

        textViewTotalAmount.setText("Total Amount for " + customerName + ":\n$" + String.format("%.2f", totalAmount));
    }

    public void sharePDF(View view) {
        // Implement PDF creation and sharing functionality here
        // This method will be called when the "Share PDF" button is clicked
        // You can use libraries like iText or AndroidPdfViewer for PDF generation
        // After generating the PDF, you can share it using intents
        Toast.makeText(this, "PDF creation and sharing functionality will be implemented here", Toast.LENGTH_SHORT).show();
    }

    private static class Item {
        private String productName;
        private int quantity;
        private double pricePerUnit;
        private double gstRate;

        public Item(String productName, int quantity, double pricePerUnit, double gstRate) {
            this.productName = productName;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
            this.gstRate = gstRate;
        }

        public double getTotalPrice() {
            return quantity * pricePerUnit * (1 + (gstRate / 100));
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPricePerUnit() {
            return pricePerUnit;
        }

        public double getGstRate() {
            return gstRate;
        }
    }
}
