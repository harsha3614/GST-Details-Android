package com.example.signuploginrealtime;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GstCalculationActivity extends AppCompatActivity {

    private EditText editTextItemName;
    private EditText editTextItemQuantity;
    private EditText editTextItemPrice;
    private EditText editTextGSTPercentage;
    private Button btnCalculate;
    private Button btnReset;
    private TextView textViewTotalAmount;
    private TextView textViewItemizedDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst_calculation);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextItemQuantity = findViewById(R.id.editTextItemQuantity);
        editTextItemPrice = findViewById(R.id.editTextItemPrice);
        editTextGSTPercentage = findViewById(R.id.editTextGSTPercentage);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        textViewItemizedDetails = findViewById(R.id.textViewItemizedDetails);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalAmount();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    private void calculateTotalAmount() {
        String itemName = editTextItemName.getText().toString().trim();
        String itemQuantityStr = editTextItemQuantity.getText().toString().trim();
        String itemPriceStr = editTextItemPrice.getText().toString().trim();
        String gstPercentageStr = editTextGSTPercentage.getText().toString().trim();

        if (!itemName.isEmpty() && !itemQuantityStr.isEmpty() && !itemPriceStr.isEmpty() && !gstPercentageStr.isEmpty()) {
            int itemQuantity = Integer.parseInt(itemQuantityStr);
            double itemPrice = Double.parseDouble(itemPriceStr);
            double gstPercentage = Double.parseDouble(gstPercentageStr);

            // Calculate total amount with GST
            double totalAmount = (itemPrice * itemQuantity) + ((itemPrice * itemQuantity) * (gstPercentage / 100));

            // Display the total amount
            String totalAmountText = "Total Amount for " + itemQuantity + " " + itemName + "(s): $" + String.format("%.2f", totalAmount);
            textViewTotalAmount.setText(totalAmountText);

            // Display itemized details
            String itemizedDetailsText = "Itemized Details:\n" +
                    "Item: " + itemName + "\n" +
                    "Quantity: " + itemQuantity + "\n" +
                    "Price per item: $" + String.format("%.2f", itemPrice) + "\n" +
                    "GST Percentage: " + gstPercentage + "%";
            textViewItemizedDetails.setText(itemizedDetailsText);
        } else {
            // If any of the fields are empty, show an error message
            textViewTotalAmount.setText("Please fill in all fields.");
            textViewItemizedDetails.setText("");
        }
    }


    private void resetFields() {
        editTextItemName.getText().clear();
        editTextItemQuantity.getText().clear();
        editTextItemPrice.getText().clear();
        editTextGSTPercentage.getText().clear();
        textViewTotalAmount.setText("Total Amount: ");
        textViewItemizedDetails.setText("Itemized Details:");
    }
}
