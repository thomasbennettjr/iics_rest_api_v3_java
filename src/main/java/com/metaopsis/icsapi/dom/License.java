package com.metaopsis.icsapi.dom;

import java.util.Arrays;

public class License {
    private String id;
    private String parentOrg;
    private CustomLicense[] customLicenses;
    private AssignedEdition[] assignedEditions;
    private CustomLimit[] customLimits;

    public License() {
    }

    public License(String id, String parentOrg) {
        this.id = id;
        this.parentOrg = parentOrg;
    }

    public License(String id, String parentOrg, CustomLicense[] customLicenses, AssignedEdition[] assignedEditions, CustomLimit[] customLimits) {
        this.id = id;
        this.parentOrg = parentOrg;
        this.customLicenses = customLicenses;
        this.assignedEditions = assignedEditions;
        this.customLimits = customLimits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(String parentOrg) {
        this.parentOrg = parentOrg;
    }

    public CustomLicense[] getCustomLicenses() {
        return customLicenses;
    }

    public void setCustomLicenses(CustomLicense[] customLicenses) {
        this.customLicenses = customLicenses;
    }

    public AssignedEdition[] getAssignedEditions() {
        return assignedEditions;
    }

    public void setAssignedEditions(AssignedEdition[] assignedEditions) {
        this.assignedEditions = assignedEditions;
    }

    public CustomLimit[] getCustomLimits() {
        return customLimits;
    }

    public void setCustomLimits(CustomLimit[] customLimits) {
        this.customLimits = customLimits;
    }

    @Override
    public String toString() {
        return "License{" +
                "id='" + id + '\'' +
                ", parentOrg='" + parentOrg + '\'' +
                ", customLicenses=" + Arrays.toString(customLicenses) +
                ", assignedEditions=" + Arrays.toString(assignedEditions) +
                ", customLimits=" + Arrays.toString(customLimits) +
                '}';
    }
}
