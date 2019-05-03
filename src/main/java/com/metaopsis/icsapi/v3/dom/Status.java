package com.metaopsis.icsapi.v3.dom;

import java.util.Arrays;

public class Status {
    private String id;
    private String createTime;
    private String updateTime;
    private String name;
    private String startTime;
    private String endTime;
    private StatusMessage status;
    private object[] objects;

    public Status() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public StatusMessage getStatus() {
        return status;
    }

    public void setStatus(StatusMessage status) {
        this.status = status;
    }

    public object[] getObjects() {
        return objects;
    }

    public void setObjects(object[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
