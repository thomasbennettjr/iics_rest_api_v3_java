package com.metaopsis.icsapi.v3.dom;

public class StatusMessage {
    private String state;
    private String message;

    public StatusMessage() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StatusMessage{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
