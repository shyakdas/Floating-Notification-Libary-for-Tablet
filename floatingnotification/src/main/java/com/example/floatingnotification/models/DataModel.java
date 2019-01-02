package com.example.floatingnotification.models;

import com.example.floatingnotification.utils.Constants;
import com.example.floatingnotification.R;

import static com.example.floatingnotification.utils.Constants.FAILED;
import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class DataModel<T> {
    private int containerType;
    private String message;
    @Constants.MessageType
    private String type;
    private T data;


    private DataModel(int containerType, String message, String type) {
        this.containerType = containerType;
        this.message = message;
        this.type = type;
    }

    private DataModel(int containerType, String message, String type, T data) {
        this(containerType, message, type);
        this.data = data;

    }

    public static <T> DataModel<T> singleMessage(String message, @Constants.MessageType String type, T data) {
        return new DataModel<>(Constants.SINGLE, message, type, data);
    }


    public static <T> DataModel<T> multipleMessage(String message, @Constants.MessageType String type, T dataModels) {
        return new DataModel<>(Constants.MULTIPLE, message, type, dataModels);
    }


    public String getMessage() {
        return message;
    }


    public int getContainerType() {
        return containerType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public int getResId() {
        if (type.equalsIgnoreCase(SUCCESS))
            return R.drawable.success;
        else if (type.equalsIgnoreCase(FAILED))
            return R.drawable.failed;
        else
            return R.drawable.conflict;
    }
}