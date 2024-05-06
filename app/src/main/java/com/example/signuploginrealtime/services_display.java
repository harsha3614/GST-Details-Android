package com.example.signuploginrealtime;

// Enterprise.java
public class services_display {
    private String enterpriseName;
    private String enterpriseType;

    // Default constructor required for Firebase
    public services_display() {
    }

    // Parameterized constructor to set values during object creation
    public services_display(String enterpriseName, String enterpriseType) {
        this.enterpriseName = enterpriseName;
        this.enterpriseType = enterpriseType;
    }

    // Getter method for retrieving enterpriseName
    public String getEnterpriseName() {
        return enterpriseName;
    }

    // Getter method for retrieving enterpriseType
    public String getEnterpriseType() {
        return enterpriseType;
    }

    // Add additional getters and setters as needed based on your database structure
    // For example:
    // public String getAnotherField() {
    //     return anotherField;
    // }
    //
    // public void setAnotherField(String anotherFieldValue) {
    //     this.anotherField = anotherFieldValue;
    // }
}
