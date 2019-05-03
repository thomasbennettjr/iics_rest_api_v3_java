package com.metaopsis.icsapi.dom;

public class ImportStatusObject {
    private ImportObject sourceObject;
    private ImportObject targetObject;
    private StatusMessage status;

    public ImportStatusObject() {
    }

    public ImportObject getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(ImportObject sourceObject) {
        this.sourceObject = sourceObject;
    }

    public ImportObject getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(ImportObject targetObject) {
        this.targetObject = targetObject;
    }

    public StatusMessage getStatus() {
        return status;
    }

    public void setStatus(StatusMessage status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ImportStatusObject{" +
                "sourceObject=" + sourceObject +
                ", targetObject=" + targetObject +
                ", status=" + status +
                '}';
    }
}
