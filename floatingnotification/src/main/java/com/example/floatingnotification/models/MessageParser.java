package com.example.floatingnotification.models;

import com.example.floatingnotification.utils.Constants;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class MessageParser {

    public static NotificationItem<String> addSingleMessage(String message, @Constants.MessageType String type) {
        return NotificationItem.singleMessage(message, type, message);
    }

    public static NotificationItem<ArrayList<NotificationItem<String>>> addMultipleMessage(ConflictData data, String message) {
        @Constants.MessageType String type = null;
        ArrayList<NotificationItem<String>> notificationItems = new ArrayList<>();
        boolean isSuccessAvailable = false;
        if (!data.getSuccess_messages().isEmpty()) {
            type = SUCCESS;
            isSuccessAvailable = true;
            for (String singleMessage : data.getSuccess_messages()) {
                notificationItems.add(NotificationItem.singleMessage(singleMessage, Constants.SUCCESS, message));
            }
        }
        if (!data.getError_messages().isEmpty()) {
            ArrayList<NotificationItem<String>> errors = new ArrayList<>();
            if (isSuccessAvailable)
                type = Constants.CONFLICT;
            else
                type = Constants.FAILED;

            for (String singleMessage : data.getError_messages()) {
                if (isSuccessAvailable)
                    errors.add(NotificationItem.singleMessage(singleMessage, Constants.CONFLICT, message));
                else {
                    errors.add(NotificationItem.singleMessage(singleMessage, Constants.FAILED, message));

                }
            }
            notificationItems.addAll(0, errors);
        }

        return NotificationItem.multipleMessage(message, type, notificationItems);
    }
}