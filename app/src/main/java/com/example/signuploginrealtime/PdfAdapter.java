
package com.example.signuploginrealtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PdfAdapter extends ArrayAdapter<PdfItem> {

    private int resourceLayout;

    public PdfAdapter(Context context, int resource, List<PdfItem> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resourceLayout, null);
        }

        PdfItem pdfItem = getItem(position);

        if (pdfItem != null) {
            TextView titleTextView = view.findViewById(R.id.pdfTitleTextView);


            if (titleTextView != null) {
                titleTextView.setText(pdfItem.getTitle());
            }


        }

        return view;
    }
}
