package com.metaopsis.icsapi.v3.dom;

public class RequestObject {
    private String id;
    private boolean includeDependencies;

    public RequestObject() {}

    public RequestObject(String id, boolean includeDependencies) {
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
        return "RequestObject{" +
                "id='" + id + '\'' +
                ", includeDependencies=" + includeDependencies +
                '}';
    }
}
