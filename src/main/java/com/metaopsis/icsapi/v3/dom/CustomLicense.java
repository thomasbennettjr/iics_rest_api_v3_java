package com.metaopsis.icsapi.v3.dom;

public class CustomLicense {
    private String licenseDef;
    private String expirationDate;
    private String licenseType;

    public CustomLicense() {
    }

    public CustomLicense(String licenseDef, String expirationDate, String licenseType) {
        this.licenseDef = licenseDef;
        this.expirationDate = expirationDate;
        this.licenseType = licenseType;
    }


    public String getLicenseDef() {
        return licenseDef;
    }

    public void setLicenseDef(String licenseDef) {
        this.licenseDef = licenseDef;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    @Override
    public String toString() {
        return "CustomLicense{" +
                "licenseDef='" + licenseDef + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", licenseType='" + licenseType + '\'' +
                '}';
    }
}
