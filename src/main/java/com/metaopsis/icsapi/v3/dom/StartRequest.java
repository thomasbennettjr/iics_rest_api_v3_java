package com.metaopsis.icsapi.v3.dom;

public class StartRequest {
    private String name;
    private Specification specification;

    public StartRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "StartRequest{" +
                "name='" + name + '\'' +
                ", specification=" + specification +
                '}';
    }
}
