package com.aparcsystems.model;

import com.aparcsystems.utils.DateFormatter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

/**
 * Created by emi91_000 on 20/02/2015.
 */
public class Penalty implements Serializable {
    @SerializedName("CitationId")
    private Long citationId;
    @SerializedName("ClientCitationNo")
    private String clientCitationNo;
    @SerializedName("IssueDateTime")
    private String issueDateTime;
    @SerializedName("OfficerId")
    private String officerId;
    @SerializedName("LicenseExpYear")
    private int licenseExpYear;
    @SerializedName("LicenseExpMonth")
    private int licenseExpMonth;
    @SerializedName("Plate")
    private String plate;
    @SerializedName("Province")
    private String province;
    @SerializedName("VehicleMake")
    private String vehicleMake;
    @SerializedName("MeterId")
    private String meterId;
    @SerializedName("Location")
    private String location;
    @SerializedName("OffenceCode")
    private String offerenceCode;
    @SerializedName("ViolationDescription")
    private String violationDescription;
    @SerializedName("PublicComment1")
    private String publicComment1;
    @SerializedName("PublicComment2")
    private String publicComment2;
    @SerializedName("PublicComment3")
    private String publicComment3;
    @SerializedName("Amount")
    private float amount;
    @SerializedName("PaidToDate")
    private float paidToDate;
    @SerializedName("IsDisputed")
    private boolean isDisputed;
    @SerializedName("IsPaid")
    private boolean isPaid;
    @SerializedName("PhotoFilesList")
    private List<String> photoList;

    public Long getCitationId() {
        return citationId;
    }

    public void setCitationId(Long citationId) {
        this.citationId = citationId;
    }

    public String getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(String issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOfferenceCode() {
        return offerenceCode;
    }

    public void setOfferenceCode(String offerenceCode) {
        this.offerenceCode = offerenceCode;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }

    public String getPublicComment1() {
        return publicComment1;
    }

    public void setPublicComment1(String publicComment1) {
        this.publicComment1 = publicComment1;
    }

    public String getPublicComment2() {
        return publicComment2;
    }

    public void setPublicComment2(String publicComment2) {
        this.publicComment2 = publicComment2;
    }

    public String getPublicComment3() {
        return publicComment3;
    }

    public void setPublicComment3(String publicComment3) {
        this.publicComment3 = publicComment3;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isDisputed() {
        return isDisputed;
    }

    public void setDisputed(boolean isDisputed) {
        this.isDisputed = isDisputed;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    public String getClientCitationNo() {
        return clientCitationNo;
    }

    public void setClientCitationNo(String clientCitationNo) {
        this.clientCitationNo = clientCitationNo;
    }

    public String getDateOfInfraction() {
        return DateFormatter.getDateToShow(DateFormatter.parseApiDate(issueDateTime));
    }
    public String getTimeOfInfraction() {
        return DateFormatter.getTimeToShow(DateFormatter.parseApiDate(issueDateTime));
    }

    public int getLicenseExpYear() {
        return licenseExpYear;
    }

    public void setLicenseExpYear(int licenseExpYear) {
        this.licenseExpYear = licenseExpYear;
    }

    public int getLicenseExpMonth() {
        return licenseExpMonth;
    }

    public void setLicenseExpMonth(int licenseExpMonth) {
        this.licenseExpMonth = licenseExpMonth;
    }

    public float getPaidToDate() {
        return paidToDate;
    }

    public void setPaidToDate(float paidToDate) {
        this.paidToDate = paidToDate;
    }
}
