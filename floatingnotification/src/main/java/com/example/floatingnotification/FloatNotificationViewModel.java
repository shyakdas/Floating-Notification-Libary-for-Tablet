package com.example.floatingnotification;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.floatingnotification.models.DataModel;

import java.util.LinkedList;
import java.util.Queue;

public class FloatNotificationViewModel extends AndroidViewModel {

    private Queue<DataModel> dataModelQueue;
    private MutableLiveData<DataModel> modelMutableLiveData;
    private final int CAPACITY = 3;
    private int count = 0;
    private static final Object LOCK = new Object();

    public FloatNotificationViewModel(@NonNull Application application) {
        super(application);
        dataModelQueue = new LinkedList<>();
        modelMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DataModel> getModelMutableLiveData() {
        return modelMutableLiveData;
    }

    public final void addNotifications(DataModel dataModel) {
        dataModelQueue.add(dataModel);
        if (count < CAPACITY)
            pushToList();

    }

    public void onClose() {
        count--;
        pushToList();
    }

    private final void pushToList() {
        if (!dataModelQueue.isEmpty()) {
            count++;
            DataModel firstModel = dataModelQueue.poll();
            modelMutableLiveData.setValue(firstModel);
        }

    }
}