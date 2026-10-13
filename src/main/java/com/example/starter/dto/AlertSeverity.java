package com.example.starter.dto;

public enum AlertSeverity {
    CRITICAL("CRITICAL"),
    WARNING("WARNING"),
    INFO("INFO");

    String data;

    AlertSeverity(String data) {
        this.data= this.data;
    }

    public String getData() {
        return data;
    }
}
