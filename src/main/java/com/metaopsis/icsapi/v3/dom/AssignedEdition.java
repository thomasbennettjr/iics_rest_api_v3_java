package com.metaopsis.icsapi.v3.dom;

public class AssignedEdition {
    private String edition;
    private String expirationDate;

    public AssignedEdition() {
    }

    public AssignedEdition(String edition, String expirationDate) {
        this.edition = edition;
        this.expirationDate = expirationDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "AssignedEdition{" +
                "edition='" + edition + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
