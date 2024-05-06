package com.example.signuploginrealtime;

public class PdfItem {
    private String title;
    private String pdfUrl;

    public PdfItem() {
        // Default constructor required for Firebase
    }

    public PdfItem(String title, String pdfUrl) {
        this.title = title;
        this.pdfUrl = pdfUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }
}
