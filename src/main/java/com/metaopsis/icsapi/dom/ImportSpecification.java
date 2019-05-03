package com.metaopsis.icsapi.dom;

import java.util.Arrays;

public class ImportSpecification {
    private DefaultConflictResolution defaultConflictResolution;
    private String[] includeObjects;
    private ObjectSpecification[] objectSpecification;

    public ImportSpecification() {
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
        return "ImportSpecification{" +
                "defaultConflictResolution=" + defaultConflictResolution +
                ", includeObjects=" + Arrays.toString(includeObjects) +
                ", objectSpecification=" + Arrays.toString(objectSpecification) +
                '}';
    }
}
