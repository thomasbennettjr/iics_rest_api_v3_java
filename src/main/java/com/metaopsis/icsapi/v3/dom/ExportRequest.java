package com.metaopsis.icsapi.v3.dom;

import java.util.Arrays;

public class ExportRequest {
    private String name;
    private RequestObject[] objects;


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

    public RequestObject[] getObjects() {
        return objects;
    }

    public void setObjects(RequestObject[] objects) {
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
