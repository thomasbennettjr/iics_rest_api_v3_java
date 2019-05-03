package com.metaopsis.icsapi.dom;

public class ExportObject {
    private String id;
    private boolean includeDependencies;

    public ExportObject() {}

    public ExportObject(String id, boolean includeDependencies) {
        this.id = id;
        this.includeDependencies = includeDependencies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIncludeDependencies() {
        return includeDependencies;
    }

    public void setIncludeDependencies(boolean includeDependencies) {
        this.includeDependencies = includeDependencies;
    }

    @Override
    public String toString() {
        return "ExportObject{" +
                "id='" + id + '\'' +
                ", includeDependencies=" + includeDependencies +
                '}';
    }
}
