package com.example.floatingnotification.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;

public class MultipleNotificationViewHolder extends NotificationViewHolder {

    private TextView mDetails;

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView, listener);
        mDetails = itemView.findViewById(R.id.detail_text);
    }

    @Override
    public void bind(DataModel dataModel) {
        super.bind(dataModel);
        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationViewHolder.stopTimer();
            }
        });
    }
}