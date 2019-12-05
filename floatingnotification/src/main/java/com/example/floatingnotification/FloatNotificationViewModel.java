package com.example.floatingnotification;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.floatingnotification.models.NotificationItem;

import java.util.LinkedList;
import java.util.Queue;

public class FloatNotificationViewModel extends AndroidViewModel {

    private static final Object LOCK = new Object();
    private final int CAPACITY = 3;
    private Queue<NotificationItem> notificationItemQueue;
    private MutableLiveData<NotificationItem> modelMutableLiveData;
    private int count = 0;

    public FloatNotificationViewModel(@NonNull Application application) {
        super(application);
        notificationItemQueue = new LinkedList<>();
        modelMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<NotificationItem> getModelMutableLiveData() {
        return modelMutableLiveData;
    }

    public final void addNotifications(NotificationItem notificationItem) {
        notificationItemQueue.add(notificationItem);
        if (count < CAPACITY)
            pushToList();

    }

    public void onClose() {
        count--;
        pushToList();
    }

    private final void pushToList() {
        if (!notificationItemQueue.isEmpty()) {
            count++;
            NotificationItem firstModel = notificationItemQueue.poll();
            modelMutableLiveData.setValue(firstModel);
        }

    }
}