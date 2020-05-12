package com.example.shyakdas.recycleranimation;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.floatingnotification.FloatNotificationViewModel;
import com.example.floatingnotification.models.NotificationItem;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.FloatingNotificationConstants.CONFLICT;
import static com.example.floatingnotification.utils.FloatingNotificationConstants.FAILED;
import static com.example.floatingnotification.utils.FloatingNotificationConstants.SUCCESS;

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
        notificationItems.add(NotificationItem.Companion.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        notificationItems.add(NotificationItem.Companion.singleMessage("Failed to checkIn meetings", FAILED, ""));
        notificationItems.add(NotificationItem.Companion.multipleMessage("Checked in successfully", CONFLICT, childData));
        childData.add(NotificationItem.Companion.singleMessage("1. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("2. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("3. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("4. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("5. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("6. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("7. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("8. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.Companion.singleMessage("9. Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("10.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("11.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("12.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("13.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("14.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("15.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("16.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("17.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("18.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("19.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("20.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("21.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("22.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("23.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("24.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("25.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("26.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("27.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("28.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("29.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("30.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("31.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("32.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.Companion.singleMessage("33.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        notificationItems.add(NotificationItem.Companion.singleMessage("4 Meetings have been checked in successfully", SUCCESS, ""));
        notificationItems.add(NotificationItem.Companion.singleMessage("Failed to checkIn meetings", FAILED, ""));
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