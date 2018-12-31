package com.example.floatingnotification.viewholders;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;

public class MultipleNotificationViewHolder extends NotificationViewHolder {

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView, listener);
    }

    @Override
    public void bind(DataModel dataModel) {
        super.bind(dataModel);

    }


}
