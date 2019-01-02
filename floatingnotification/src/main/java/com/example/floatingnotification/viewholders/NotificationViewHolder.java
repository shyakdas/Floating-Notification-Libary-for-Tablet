package com.example.floatingnotification.viewholders;

import android.os.CountDownTimer;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;

public abstract class NotificationViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private TextView messageTextView;
    private static  CountDownTimer timer;
    private TextView mCloseNotification;
    private OnCloseListener onCloseListener;

    public NotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.imageView);
        messageTextView = itemView.findViewById(R.id.message_text);
        mCloseNotification = itemView.findViewById(R.id.close_notification);
        onCloseListener = listener;
    }

    @CallSuper
    public void bind(DataModel dataModel) {
        mImageView.setImageResource(dataModel.getResId());
        messageTextView.setText(dataModel.getMessage());
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

    public static void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    protected void close() {
        onCloseListener.onClose(getAdapterPosition());
    }
}