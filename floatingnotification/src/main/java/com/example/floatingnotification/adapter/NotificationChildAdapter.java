package com.example.floatingnotification.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.floatingnotification.R;
import com.example.floatingnotification.models.DataModel;
import com.example.floatingnotification.viewholders.NotificationChildHolder;

import java.util.ArrayList;

public class NotificationChildAdapter extends RecyclerView.Adapter<NotificationChildHolder> {

    private ArrayList<DataModel> dataModelArrayList;

    public NotificationChildAdapter(ArrayList<DataModel> dataModels) {
        this.dataModelArrayList = dataModels;
    }

    @NonNull
    @Override
    public NotificationChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new NotificationChildHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notification_child_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationChildHolder notificationItemHolder, int position) {
        notificationItemHolder.bindView(dataModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}
