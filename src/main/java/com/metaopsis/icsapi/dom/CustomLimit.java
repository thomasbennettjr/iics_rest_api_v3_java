package com.metaopsis.icsapi.dom;

public class CustomLimit {
    private String limitDefinition;
    private Integer value;

    public CustomLimit() {}

    public CustomLimit(String limitDefinition, Integer value) {
        this.limitDefinition = limitDefinition;
        this.value = value;
    }

    public String getLimitDefinition() {
        return limitDefinition;
    }

    public void setLimitDefinition(String limitDefinition) {
        this.limitDefinition = limitDefinition;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CustomLimit{" +
                "limitDefinition='" + limitDefinition + '\'' +
                ", value=" + value +
                '}';
    }
}
