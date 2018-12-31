package com.example.floatingnotification.viewholders;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.floatingnotification.DataModel;
import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;

public class ConflictViewHolder extends RecyclerView.ViewHolder {

    private TextView mText;
    private CountDownTimer timer;
    private OnCloseListener onCloseListener;

    public ConflictViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView);
        onCloseListener = listener;
        mText = itemView.findViewById(R.id.message_text);
    }

    public void bind(DataModel dataModel) {
        mText.setText(dataModel.getMessage());
        startTimer();

    }

    private void startTimer() {
        timer = new CountDownTimer(6000, 6000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                onCloseListener.onClose(getAdapterPosition());
            }
        }.start();
    }
}
