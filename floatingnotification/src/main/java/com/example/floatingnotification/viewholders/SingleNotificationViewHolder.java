package com.example.floatingnotification.viewholders;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.floatingnotification.listener.OnCloseListener;

public class SingleNotificationViewHolder extends NotificationViewHolder {

    public SingleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView, listener);
    }
}