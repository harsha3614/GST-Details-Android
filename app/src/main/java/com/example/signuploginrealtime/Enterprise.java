package com.example.signuploginrealtime;



public class Enterprise {

    private String enterpriseName;
    private String enterpriseType;
    private String registrationNumber;
    private String address;
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private String gstIdentificationNumber;
    private String taxRegistrationType;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String designation;
    private String natureOfBusiness;
    private String panCard;
    private String aadhaarCard;

    // Default constructor required for Firebase
    public Enterprise() {
    }

    public Enterprise(String enterpriseName, String enterpriseType, String registrationNumber, String address,
                      String emailAddress, String password, String phoneNumber, String gstIdentificationNumber,
                      String taxRegistrationType, String bankName, String accountNumber, String ifscCode,
                      String designation, String natureOfBusiness, String panCard, String aadhaarCard) {
        this.enterpriseName = enterpriseName;
        this.enterpriseType = enterpriseType;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gstIdentificationNumber = gstIdentificationNumber;
        this.taxRegistrationType = taxRegistrationType;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.designation = designation;
        this.natureOfBusiness = natureOfBusiness;
        this.panCard = panCard;
        this.aadhaarCard = aadhaarCard;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGstIdentificationNumber() {
        return gstIdentificationNumber;
    }

    public void setGstIdentificationNumber(String gstIdentificationNumber) {
        this.gstIdentificationNumber = gstIdentificationNumber;
    }

    public String getTaxRegistrationType() {
        return taxRegistrationType;
    }

    public void setTaxRegistrationType(String taxRegistrationType) {
        this.taxRegistrationType = taxRegistrationType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getAadhaarCard() {
        return aadhaarCard;
    }

    public void setAadhaarCard(String aadhaarCard) {
        this.aadhaarCard = aadhaarCard;
    }
}
