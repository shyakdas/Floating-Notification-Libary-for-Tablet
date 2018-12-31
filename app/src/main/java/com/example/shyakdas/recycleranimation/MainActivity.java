package com.example.shyakdas.recycleranimation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.floatingnotification.FloatNotificationViewModel;
import com.example.floatingnotification.models.DataModel;

import java.util.ArrayList;

import static com.example.floatingnotification.Constants.CONFLICT;
import static com.example.floatingnotification.Constants.FAILED;
import static com.example.floatingnotification.Constants.SUCCESS;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataModels;
    private int position;
    private FloatNotificationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataModels = new ArrayList<>();
        dataModels.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        dataModels.add(DataModel.singleMessage("Failed to checkIn meetings", FAILED, ""));
        dataModels.add(DataModel.singleMessage("4 Meetings have been checked in successfully", CONFLICT, ""));
        dataModels.add(DataModel.singleMessage("Failed to checkIn meetings", FAILED, ""));
        dataModels.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        dataModels.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        position = 0;
        viewModel = ViewModelProviders.of(this).get(FloatNotificationViewModel.class);
        goToQueue();
    }

    public void goToQueue() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (position < dataModels.size()) {
                    viewModel.addNotifications(dataModels.get(position));
                }
                position++;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}