package com.metaopsis.icsapi.dom;

public class ImportStartRequest {
    private String name;
    private ImportSpecification importSpecification;

    public ImportStartRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImportSpecification getImportSpecification() {
        return importSpecification;
    }

    public void setImportSpecification(ImportSpecification importSpecification) {
        this.importSpecification = importSpecification;
    }

    @Override
    public String toString() {
        return "ImportStartRequest{" +
                "name='" + name + '\'' +
                ", importSpecification=" + importSpecification +
                '}';
    }
}
