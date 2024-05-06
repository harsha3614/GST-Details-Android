package com.example.signuploginrealtime;

public class Service {

    private String serviceName;
    private String description;
    private double serviceCharge;
    private String documentsList;

    // Default constructor (required for Firebase)
    public Service() {
        // Default constructor is needed for Firebase
    }

    public Service(String serviceName, String description, double serviceCharge, String documentsList) {
        this.serviceName = serviceName;
        this.description = description;
        this.serviceCharge = serviceCharge;
        this.documentsList = documentsList;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(String documentsList) {
        this.documentsList = documentsList;
    }
}

