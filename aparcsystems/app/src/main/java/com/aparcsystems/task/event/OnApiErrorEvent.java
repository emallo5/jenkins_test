package com.aparcsystems.task.event;

public class OnApiErrorEvent {
    public String errorMessage;
    public OnApiErrorEvent(String errorMessage) {
        this.errorMessage=errorMessage;
    }
}
