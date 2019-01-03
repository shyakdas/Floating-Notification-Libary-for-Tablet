package com.example.floatingnotification.models;

import com.example.floatingnotification.R;

import static com.example.floatingnotification.utils.Constants.FAILED;
import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class SubNotificationItem extends NotificationItem {

    protected SubNotificationItem(int containerType, String message, String type) {
        super(containerType, message, type);
    }

    @Override
    public int getResId() {
        if (type.equalsIgnoreCase(SUCCESS))
            return R.drawable.sub_notification_conflict;
        else if (type.equalsIgnoreCase(FAILED))
            return R.drawable.notification_failed;
        else
            return R.drawable.sub_notification_conflict;
    }
}