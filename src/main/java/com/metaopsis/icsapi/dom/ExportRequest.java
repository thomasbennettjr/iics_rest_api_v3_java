package com.metaopsis.icsapi.dom;

import java.util.Arrays;

public class ExportRequest {
    private String name;
    private ExportObject[] objects;


    public ExportRequest() {
    }

    public ExportRequest(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExportObject[] getObjects() {
        return objects;
    }

    public void setObjects(ExportObject[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "ExportRequest{" +
                "name='" + name + '\'' +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
