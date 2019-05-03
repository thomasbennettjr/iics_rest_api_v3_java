package com.metaopsis.icsapi.dom;

import java.util.Arrays;

public class Lookup {
    private object[] objects;

    public Lookup() {
    }

    public object[] getObjects() {
        return objects;
    }

    public void setObjects(object[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "Lookup{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }


}
