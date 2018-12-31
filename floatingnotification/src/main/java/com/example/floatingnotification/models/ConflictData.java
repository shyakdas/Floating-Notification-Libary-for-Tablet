package com.example.floatingnotification.models;

import java.util.ArrayList;

public class ConflictData {
    private ArrayList<String> success_messages;
    private ArrayList<String> error_messages;

    public ArrayList<String> getSuccess_messages() {
        return success_messages;
    }

    public void setSuccess_messages(ArrayList<String> success_messages) {
        this.success_messages = success_messages;
    }

    public ArrayList<String> getError_messages() {
        if (error_messages == null) error_messages = new ArrayList<>(0);
        return error_messages;
    }

    public void setError_messages(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
    }
}
