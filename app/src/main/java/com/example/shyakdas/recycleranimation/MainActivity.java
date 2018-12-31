package com.example.shyakdas.recycleranimation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.floatingnotification.Constant;
import com.example.floatingnotification.DataModel;
import com.example.floatingnotification.FloatNotificationViewModel;

import java.util.ArrayList;

import static com.example.floatingnotification.Constant.CONFLICT;
import static com.example.floatingnotification.Constant.FAILED;
import static com.example.floatingnotification.Constant.SUCCESS;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataModels;
    private int position;
    private FloatNotificationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModels = new ArrayList<>();
        dataModels.add(new DataModel("1", "Selected user checked-in successfully to Meeting ID #34", SUCCESS));
        dataModels.add(new DataModel("2", "Failed to checkIn meetings", FAILED));
        dataModels.add(new DataModel("3", "4 Meetings have been checked in successfully", CONFLICT));
        dataModels.add(new DataModel("4", "Failed to checkIn meetings", FAILED));
        dataModels.add(new DataModel("5", "Selected user checked-in successfully to Meeting ID #34", SUCCESS));
        dataModels.add(new DataModel("6", "Selected user checked-in successfully to Meeting ID #34", SUCCESS));
        dataModels.add(new DataModel("7", "4 Meetings have been checked in successfully", CONFLICT));
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
                    Log.d("CountDownTimer", dataModels.get(position).getId());
                }
                position++;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}