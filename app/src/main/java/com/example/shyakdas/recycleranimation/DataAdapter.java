package com.example.shyakdas.recycleranimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.AdapterViewHolder> {

    private static final String TAG = DataAdapter.class.getName();
    private Context mContext;
    private ArrayList<DataModel> mDataList;
    private int lastPosition = -1;
    private OnClickItemListener listener;
    private View itemView;

    public DataAdapter(Context mContext, ArrayList<DataModel> dataModels, OnClickItemListener itemListener) {
        this.mContext = mContext;
        this.listener = itemListener;
        mDataList = dataModels;
    }

    public void remove(int position) {
        if (position > -1) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DataAdapter.AdapterViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterViewHolder adapterViewHolder,
                                 @SuppressLint("RecyclerView") final int position) {
        itemView = adapterViewHolder.itemView;
        adapterViewHolder.bind(position);
        setInAnimation(itemView, position);
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

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mText;
        CountDownTimer timer;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.text);
        }

        public void bind(int position) {
            mText.setText(mDataList.get(position).getName());
            startTimer();
        }

        private void startTimer() {
            timer = new CountDownTimer(6000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.e(TAG, "millisUntilFinished==" + getAdapterPosition() + "time==" + millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    listener.onItemClick(getAdapterPosition());
                }
            }.start();
        }
    }
}