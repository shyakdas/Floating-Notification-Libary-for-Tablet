package com.example.floatingnotification.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;
import com.example.floatingnotification.viewholders.MultipleNotificationViewHolder;
import com.example.floatingnotification.viewholders.NotificationViewHolder;
import com.example.floatingnotification.viewholders.SingleNotificationViewHolder;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.MULTIPLE;
import static com.example.floatingnotification.utils.Constants.SINGLE;

public class DataAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private static final String TAG = DataAdapter.class.getName();
    private Context mContext;
    private ArrayList<DataModel> mDataList;
    private int lastPosition = -1;
    private OnCloseListener listener;

    public DataAdapter(Context mContext, ArrayList<DataModel> dataModels, OnCloseListener itemListener) {
        this.mContext = mContext;
        this.listener = itemListener;
        mDataList = dataModels;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        switch (itemType) {
            case SINGLE:
                return new SingleNotificationViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.single_notification_item, viewGroup, false), listener);
            case MULTIPLE:
                return new MultipleNotificationViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.multiple_notification_item, viewGroup, false), listener,mContext);

            default:
                return new SingleNotificationViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.single_notification_item, viewGroup, false), listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int position) {
        notificationViewHolder.bind(mDataList.get(position));
        setInAnimation(notificationViewHolder.itemView, position);
    }


    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getContainerType();
    }

    private void setInAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.layout_animation_slide_right);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}