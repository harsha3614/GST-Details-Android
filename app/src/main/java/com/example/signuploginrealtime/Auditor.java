package com.example.signuploginrealtime;



public class Auditor {

    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String confidentialAddress;
    private String auditorAIN;
    private String membershipNumber;
    private String dateOfEnrolment;
    private String auditorType;
    private String natureOfAudit;
    private String panCard;
    private String aadhaarCard;

    // Default constructor required for Firebase
    public Auditor() {
    }

    public Auditor(String fullName, String dateOfBirth, String gender, String contactNumber, String emailAddress,
                   String confidentialAddress, String auditorAIN, String membershipNumber, String dateOfEnrolment,
                   String auditorType, String natureOfAudit, String panCard, String aadhaarCard) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.confidentialAddress = confidentialAddress;
        this.auditorAIN = auditorAIN;
        this.membershipNumber = membershipNumber;
        this.dateOfEnrolment = dateOfEnrolment;
        this.auditorType = auditorType;
        this.natureOfAudit = natureOfAudit;
        this.panCard = panCard;
        this.aadhaarCard = aadhaarCard;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getConfidentialAddress() {
        return confidentialAddress;
    }

    public void setConfidentialAddress(String confidentialAddress) {
        this.confidentialAddress = confidentialAddress;
    }

    public String getAuditorAIN() {
        return auditorAIN;
    }

    public void setAuditorAIN(String auditorAIN) {
        this.auditorAIN = auditorAIN;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getDateOfEnrolment() {
        return dateOfEnrolment;
    }

    public void setDateOfEnrolment(String dateOfEnrolment) {
        this.dateOfEnrolment = dateOfEnrolment;
    }

    public String getAuditorType() {
        return auditorType;
    }

    public void setAuditorType(String auditorType) {
        this.auditorType = auditorType;
    }

    public String getNatureOfAudit() {
        return natureOfAudit;
    }

    public void setNatureOfAudit(String natureOfAudit) {
        this.natureOfAudit = natureOfAudit;
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
