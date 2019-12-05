package com.example.floatingnotification.viewholders;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.NotificationItem;

public abstract class NotificationViewHolder extends RecyclerView.ViewHolder {

    private static CountDownTimer timer;
    private ImageView mImageView;
    private TextView messageTextView;
    private TextView mCloseNotification;
    private OnCloseListener onCloseListener;

    public NotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.imageView);
        messageTextView = itemView.findViewById(R.id.message_text);
        mCloseNotification = itemView.findViewById(R.id.close_notification);
        onCloseListener = listener;
    }

    public static void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @CallSuper
    public void bind(NotificationItem notificationItem) {
        mImageView.setImageResource(notificationItem.getResId());
        messageTextView.setText(notificationItem.getMessage());
        startTimer();
        mCloseNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                close();
            }
        });
    }

    public void startTimer() {
        timer = new CountDownTimer(6000, 6000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                close();
            }
        }.start();
    }

    protected void close() {
        onCloseListener.onClose(getAdapterPosition());
    }
}