package com.example.floatingnotification.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.floatingnotification.Constant;
import com.example.floatingnotification.DataModel;
import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.viewholders.ConflictViewHolder;
import com.example.floatingnotification.viewholders.FailedViewHolder;
import com.example.floatingnotification.viewholders.SuccessViewHolder;

import java.util.ArrayList;

import static com.example.floatingnotification.Constant.CONFLICT_INT;
import static com.example.floatingnotification.Constant.FAILED_INT;
import static com.example.floatingnotification.Constant.SUCCESS_INT;

public class DataAdapter extends RecyclerView.Adapter {

    private static final String TAG = DataAdapter.class.getName();
    private Context mContext;
    private ArrayList<DataModel> mDataList;
    private int lastPosition = -1;
    private OnCloseListener listener;
    private View itemView;


    public DataAdapter(Context mContext, ArrayList<DataModel> dataModels, OnCloseListener itemListener) {
        this.mContext = mContext;
        this.listener = itemListener;
        mDataList = dataModels;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        switch (itemType) {
            case SUCCESS_INT:
                return new SuccessViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.success_item, viewGroup, false), listener);

            case FAILED_INT:
                return new FailedViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.failed_item, viewGroup, false), listener);

            case CONFLICT_INT:
                return new ConflictViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.conflict_item, viewGroup, false), listener);

            default:
                return new SuccessViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.success_item, viewGroup, false), listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)) {
            case SUCCESS_INT:
                itemView = viewHolder.itemView;
                ((SuccessViewHolder) viewHolder).bind(mDataList.get(position));
                setInAnimation(itemView, position);
                break;

            case FAILED_INT:
                itemView = viewHolder.itemView;
                ((FailedViewHolder) viewHolder).bind(mDataList.get(position));
                setInAnimation(itemView, position);
                break;

            case CONFLICT_INT:
                itemView = viewHolder.itemView;
                ((ConflictViewHolder) viewHolder).bind(mDataList.get(position));
                setInAnimation(itemView, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position).getType().equalsIgnoreCase(Constant.SUCCESS))
            return SUCCESS_INT;

        else if (mDataList.get(position).getType().equalsIgnoreCase(Constant.FAILED))

            return FAILED_INT;

        else if (mDataList.get(position).getType().equalsIgnoreCase(Constant.CONFLICT))

            return CONFLICT_INT;

        else
            return SUCCESS_INT;
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