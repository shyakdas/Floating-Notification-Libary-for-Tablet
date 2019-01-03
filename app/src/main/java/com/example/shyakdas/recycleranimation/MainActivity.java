package com.example.shyakdas.recycleranimation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.floatingnotification.FloatNotificationViewModel;
import com.example.floatingnotification.models.MessageParser;
import com.example.floatingnotification.models.NotificationItem;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.CONFLICT;
import static com.example.floatingnotification.utils.Constants.FAILED;
import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NotificationItem> notificationItems;
    private int position;
    private FloatNotificationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationItems = new ArrayList<>();
        ArrayList<NotificationItem<String>> childData = new ArrayList<>();
        notificationItems.add(NotificationItem.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        notificationItems.add(NotificationItem.singleMessage("Failed to checkIn meetings", FAILED, ""));
        notificationItems.add(NotificationItem.singleMessage("4 Meetings have been checked in successfully", SUCCESS, ""));
        notificationItems.add(NotificationItem.singleMessage("Failed to checkIn meetings", FAILED, ""));
        childData.add(NotificationItem.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        notificationItems.add(NotificationItem.multipleMessage("Checked in successfully", CONFLICT, childData));
        position = 0;
        viewModel = ViewModelProviders.of(this).get(FloatNotificationViewModel.class);
        goToQueue();
    }

    public void goToQueue() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (position < notificationItems.size()) {
                    viewModel.addNotifications(notificationItems.get(position));
                }
                position++;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}