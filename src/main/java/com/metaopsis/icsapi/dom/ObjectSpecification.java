package com.metaopsis.icsapi.dom;

public class ObjectSpecification {
    private String sourceObjectId;
    private DefaultConflictResolution conflictResolution;
    private String targetObjectId;

    public ObjectSpecification() {
    }

    public String getSourceObjectId() {
        return sourceObjectId;
    }

    public void setSourceObjectId(String sourceObjectId) {
        this.sourceObjectId = sourceObjectId;
    }

    public DefaultConflictResolution getConflictResolution() {
        return conflictResolution;
    }

    public void setConflictResolution(DefaultConflictResolution conflictResolution) {
        this.conflictResolution = conflictResolution;
    }

    public String getTargetObjectId() {
        return targetObjectId;
    }

    public void setTargetObjectId(String targetObjectId) {
        this.targetObjectId = targetObjectId;
    }

    @Override
    public String toString() {
        return "ObjectSpecification{" +
                "sourceObjectId='" + sourceObjectId + '\'' +
                ", conflictResolution=" + conflictResolution +
                ", targetObjectId='" + targetObjectId + '\'' +
                '}';
    }
}
