package com.metaopsis.icsapi.v3.dom;

import java.util.Arrays;

public class Specification {
    private DefaultConflictResolution defaultConflictResolution;
    private String[] includeObjects;
    private ObjectSpecification[] objectSpecification;

    public Specification() {
    }

    public DefaultConflictResolution getDefaultConflictResolution() {
        return defaultConflictResolution;
    }

    public void setDefaultConflictResolution(DefaultConflictResolution defaultConflictResolution) {
        this.defaultConflictResolution = defaultConflictResolution;
    }

    public String[] getIncludeObjects() {
        return includeObjects;
    }

    public void setIncludeObjects(String[] includeObjects) {
        this.includeObjects = includeObjects;
    }

    public ObjectSpecification[] getObjectSpecification() {
        return objectSpecification;
    }

    public void setObjectSpecification(ObjectSpecification[] objectSpecification) {
        this.objectSpecification = objectSpecification;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "defaultConflictResolution=" + defaultConflictResolution +
                ", includeObjects=" + Arrays.toString(includeObjects) +
                ", objectSpecification=" + Arrays.toString(objectSpecification) +
                '}';
    }
}
