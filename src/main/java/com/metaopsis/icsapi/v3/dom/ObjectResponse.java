package com.metaopsis.icsapi.v3.dom;

import java.util.Arrays;

public class ObjectResponse {
    private int count;
    private object[] objects;

    public ObjectResponse() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public object[] getObjects() {
        return objects;
    }

    public void setObjects(object[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "ObjectResponse{" +
                "count=" + count +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
