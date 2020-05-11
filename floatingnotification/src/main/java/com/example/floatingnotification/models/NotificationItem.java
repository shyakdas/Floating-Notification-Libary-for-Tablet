package com.example.floatingnotification.models;

import com.example.floatingnotification.R;
import com.example.floatingnotification.utils.FloatingNotificationConstants;

public class NotificationItem<T> {
    private int containerType;
    private String message;
    @FloatingNotificationConstants.Companion.MessageType
    private String type;
    private T data;

    protected NotificationItem(int containerType, String message, String type) {
        this.containerType = containerType;
        this.message = message;
        this.type = type;
    }

    protected NotificationItem(int containerType, String message, String type, T data) {
        this(containerType, message, type);
        this.data = data;

    }

    public static <T> NotificationItem<T> singleMessage(String message,
                                                        @FloatingNotificationConstants
                                                                .Companion.MessageType String type,
                                                        T data) {
        return new NotificationItem<>(FloatingNotificationConstants.SINGLE, message, type, data);
    }


    public static <T> NotificationItem<T> multipleMessage(String message,
                                                          @FloatingNotificationConstants
                                                                  .Companion.MessageType
                                                                  String type, T dataModels) {
        return new NotificationItem<>(FloatingNotificationConstants.MULTIPLE, message, type, dataModels);
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
        if (type.equalsIgnoreCase(FloatingNotificationConstants.SUCCESS))
            return R.drawable.notification_success;
        else if (type.equalsIgnoreCase(FloatingNotificationConstants.FAILED))
            return R.drawable.notification_failed;
        else
            return R.drawable.notification_conflict;
    }

    public int getSubResId() {
        if (type.equalsIgnoreCase(FloatingNotificationConstants.SUCCESS))
            return R.drawable.sub_notification_success;
        else if (type.equalsIgnoreCase(FloatingNotificationConstants.FAILED))
            return R.drawable.notification_failed;
        else
            return R.drawable.sub_notification_conflict;
    }
}