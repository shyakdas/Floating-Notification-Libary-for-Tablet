package com.example.floatingnotification.models;

import com.example.floatingnotification.utils.FloatingNotificationConstants;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.FloatingNotificationConstants.SUCCESS;

public class MessageParser {

    public static NotificationItem<String> addSingleMessage(String message, @FloatingNotificationConstants.Companion.MessageType String type) {
        return NotificationItem.singleMessage(message, type, message);
    }

    public static NotificationItem<ArrayList<NotificationItem<String>>> addMultipleMessage(ConflictData data, String message) {
        @FloatingNotificationConstants.Companion.MessageType String type = null;
        ArrayList<NotificationItem<String>> notificationItems = new ArrayList<>();
        boolean isSuccessAvailable = false;
        if (!data.getSuccess_messages().isEmpty()) {
            type = SUCCESS;
            isSuccessAvailable = true;
            for (String singleMessage : data.getSuccess_messages()) {
                notificationItems.add(NotificationItem.singleMessage(singleMessage, FloatingNotificationConstants.SUCCESS, message));
            }
        }
        if (!data.getError_messages().isEmpty()) {
            ArrayList<NotificationItem<String>> errors = new ArrayList<>();
            if (isSuccessAvailable)
                type = FloatingNotificationConstants.CONFLICT;
            else
                type = FloatingNotificationConstants.FAILED;

            for (String singleMessage : data.getError_messages()) {
                if (isSuccessAvailable)
                    errors.add(NotificationItem.singleMessage(singleMessage, FloatingNotificationConstants.CONFLICT, message));
                else {
                    errors.add(NotificationItem.singleMessage(singleMessage, FloatingNotificationConstants.FAILED, message));
                }
            }
            notificationItems.addAll(0, errors);
        }
        return NotificationItem.multipleMessage(message, type, notificationItems);
    }
}