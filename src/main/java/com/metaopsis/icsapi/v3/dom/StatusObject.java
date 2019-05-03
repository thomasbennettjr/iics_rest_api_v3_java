package com.metaopsis.icsapi.v3.dom;

public class StatusObject {
    private AssetObject sourceObject;
    private AssetObject targetObject;
    private StatusMessage status;

    public StatusObject() {
    }

    public AssetObject getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(AssetObject sourceObject) {
        this.sourceObject = sourceObject;
    }

    public AssetObject getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(AssetObject targetObject) {
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
        return "StatusObject{" +
                "sourceObject=" + sourceObject +
                ", targetObject=" + targetObject +
                ", status=" + status +
                '}';
    }
}
