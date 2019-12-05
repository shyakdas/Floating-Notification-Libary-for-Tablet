package com.example.floatingnotification.viewholders;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.floatingnotification.listener.OnCloseListener;

public class SingleNotificationViewHolder extends NotificationViewHolder {

    public SingleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView, listener);
    }
}