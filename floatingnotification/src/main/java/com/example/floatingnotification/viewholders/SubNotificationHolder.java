package com.example.floatingnotification.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.models.NotificationItem;

public class SubNotificationHolder extends RecyclerView.ViewHolder {

    private ImageView mNotificationImage;
    private TextView mNotificationText;

    public SubNotificationHolder(@NonNull View itemView) {
        super(itemView);
        mNotificationText = itemView.findViewById(R.id.message_text);
        mNotificationImage = itemView.findViewById(R.id.imageView);
    }

    public void bindView(NotificationItem notificationItem) {
        mNotificationImage.setImageResource(notificationItem.getSubResId());
        mNotificationText.setText(notificationItem.getMessage());
    }
}