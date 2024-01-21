package com.source.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="CUSTOMER_DATA")
public class CustomerData {

    @Id
    @Column(name="TRACKING_ID",unique = true)
    private int trackingId;

    @Column(name="NAME")
    private String name;

    @Column(name="PARENT NAME")
    private String parentName;

    @Column(name="PLACE")
    private String place;

    @Column(name="MATERIAL_NATURE")
    private String  materialNature;

    @Column(name="MATERIAL_TYPE")
    private String materialType;

    @Column(name="WEIGHT_IN_GRAMS")
    private String weightInGrams;

    @Column(name="AMOUNT_GIVEN")
    private String amountGiven;

    @Column(name="MATERIAL_MARKET_VALUE")
    private String materialMarketValue;

    @Column(name="REQUESTED_DATE")
    private Date requestedDate;

    @Column(name="RETURNED_DATE")
    private Date returnedDate;

    @Column(name="REPORT_STATUS")
    private String reportStatus;

    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    @Column(name="COMMENTS")
    private String comments;

    @Column(name="AMOUNT_COLLECTED")
    private String returnedAmount;

    @Column(name="INTEREST_AMOUNT")
    private String interestAmount;

    @Column(name="RECEIPT_NAME")
    private String receiptName;


    @Column(name="STATUS")
    private String status;



    @Column(name="RESULT")
    private String result;




    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMaterialNature() {
        return materialNature;
    }

    public void setMaterialNature(String materialNature) {
        this.materialNature = materialNature;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(String weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public String getAmountGiven() {
        return amountGiven;
    }

    public void setAmountGiven(String amountGiven) {
        this.amountGiven = amountGiven;
    }

    public String getMaterialMarketValue() {
        return materialMarketValue;
    }

    public void setMaterialMarketValue(String materialMarketValue) {
        this.materialMarketValue = materialMarketValue;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReturnedAmount() {
        return returnedAmount;
    }

    public void setReturnedAmount(String returnedAmount) {
        this.returnedAmount = returnedAmount;
    }

    public String getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(String interestAmount) {
        this.interestAmount = interestAmount;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "trackingId=" + trackingId +
                ", name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", place='" + place + '\'' +
                ", materialNature='" + materialNature + '\'' +
                ", materialType='" + materialType + '\'' +
                ", weightInGrams='" + weightInGrams + '\'' +
                ", amountGiven='" + amountGiven + '\'' +
                ", materialMarketValue='" + materialMarketValue + '\'' +
                ", requestedDate=" + requestedDate +
                ", returnedDate=" + returnedDate +
                ", reportStatus='" + reportStatus + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", comments='" + comments + '\'' +
                ", returnedAmount='" + returnedAmount + '\'' +
                ", interestAmount='" + interestAmount + '\'' +
                ", receiptName='" + receiptName + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String toReport() {
        return trackingId + "|" +
                name + "|" +
                parentName + "|" +
                place + "|" +
                materialNature + "|" +
                materialType + "|" +
                weightInGrams + "|" +
                amountGiven + "|" +
                materialMarketValue + "|" +
                requestedDate + "|" +
                returnedDate + "|" +
                reportStatus + "|" +
                phoneNumber + "|" +
                comments + "|" +
                returnedAmount + "|" +
                interestAmount + "|" +
                receiptName + "|" +
                status + "|" +
                result;
    }

}
