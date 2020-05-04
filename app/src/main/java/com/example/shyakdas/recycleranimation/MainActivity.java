package com.example.shyakdas.recycleranimation;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.floatingnotification.FloatNotificationViewModelKt;
import com.example.floatingnotification.models.NotificationItem;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.CONFLICT;
import static com.example.floatingnotification.utils.Constants.FAILED;
import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NotificationItem> notificationItems;
    private int position;
    private FloatNotificationViewModelKt viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationItems = new ArrayList<>();
        ArrayList<NotificationItem<String>> childData = new ArrayList<>();
        notificationItems.add(NotificationItem.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        notificationItems.add(NotificationItem.singleMessage("Failed to checkIn meetings", FAILED, ""));
        notificationItems.add(NotificationItem.multipleMessage("Checked in successfully", CONFLICT, childData));
        childData.add(NotificationItem.singleMessage("1. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("2. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("3. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("4. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("5. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("6. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("7. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("8. Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(NotificationItem.singleMessage("9. Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("10.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("11.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("12.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("13.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("14.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("15.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("16.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("17.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("18.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("19.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("20.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("21.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("22.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("23.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("24.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("25.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("26.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("27.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("28.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("29.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("30.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("31.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("32.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        childData.add(NotificationItem.singleMessage("33.Selected user checked-in successfully to Meeting ID #34", FAILED, ""));
        notificationItems.add(NotificationItem.singleMessage("4 Meetings have been checked in successfully", SUCCESS, ""));
        notificationItems.add(NotificationItem.singleMessage("Failed to checkIn meetings", FAILED, ""));
        position = 0;
        viewModel = ViewModelProviders.of(this).get(FloatNotificationViewModelKt.class);
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