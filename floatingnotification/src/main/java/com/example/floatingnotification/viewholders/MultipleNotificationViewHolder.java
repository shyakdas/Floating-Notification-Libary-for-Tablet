package com.example.floatingnotification.viewholders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.adapter.DataAdapter;
import com.example.floatingnotification.adapter.NotificationChildAdapter;
import com.example.floatingnotification.animator.SlideInOutRightItemAnimator;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.CONFLICT;
import static com.example.floatingnotification.utils.Constants.FAILED;
import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class MultipleNotificationViewHolder extends NotificationViewHolder {

    private TextView mDetails;
    private View mExpandBorderView;
    private int originalHeight = 0;
    private boolean isViewExpanded = false;
    private RecyclerView mExpandRecyclerView;
    private boolean mIsViewExpanded = false;
    private NotificationChildAdapter mNotificationChildAdapter;
    private Context mContext;
    private ArrayList<DataModel> tempData;

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener, Context context) {
        super(itemView, listener);
        this.mContext = context;
        mDetails = itemView.findViewById(R.id.detail_text);
        mExpandRecyclerView = itemView.findViewById(R.id.recycler_view);
        mExpandBorderView = itemView.findViewById(R.id.expand_view);
    }

    @Override
    public void bind(DataModel dataModel) {
        super.bind(dataModel);
        if (!isViewExpanded) {
            mExpandRecyclerView.setVisibility(View.GONE);
            mExpandBorderView.setVisibility(View.GONE);
            mExpandRecyclerView.setEnabled(false);
        }

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details();
            }
        });
    }

    private void details() {
        NotificationViewHolder.stopTimer();
        // If the originalHeight is 0 then find the height of the View being used
        // This would be the height of the cardView
        if (originalHeight == 0) {
            originalHeight = itemView.getHeight();
        }
        // Declare a ValueAnimator object
        ValueAnimator valueAnimator;
        if (!mIsViewExpanded) {
            initAdapter();
            mExpandRecyclerView.setVisibility(View.VISIBLE);
            mExpandBorderView.setVisibility(View.VISIBLE);
            mExpandRecyclerView.setEnabled(true);
            mIsViewExpanded = true;
            valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 2.0));
            // These values in this method can be changed to expand however much you like
        } else {
            mIsViewExpanded = false;
            valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 2.0), originalHeight);
            Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out
            a.setDuration(200);
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mExpandRecyclerView.setVisibility(View.INVISIBLE);
                    mExpandBorderView.setVisibility(View.INVISIBLE);
                    mExpandRecyclerView.setEnabled(false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mExpandRecyclerView.startAnimation(a);
        }
        valueAnimator.setDuration(200);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                itemView.getLayoutParams().height = value.intValue();
                itemView.requestLayout();
            }
        });
        valueAnimator.start();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mExpandRecyclerView.setLayoutManager(linearLayoutManager);
        mExpandRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mExpandRecyclerView));
        mNotificationChildAdapter = new NotificationChildAdapter(getTempData());
        mExpandRecyclerView.setAdapter(mNotificationChildAdapter);
    }

    public ArrayList<DataModel> getTempData() {
        ArrayList<DataModel<String>> childData = new ArrayList<>();
        tempData = new ArrayList<>();
        tempData.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        tempData.add(DataModel.singleMessage("Failed to checkIn meetings", FAILED, ""));
        tempData.add(DataModel.singleMessage("4 Meetings have been checked in successfully", SUCCESS, ""));
        tempData.add(DataModel.singleMessage("Failed to checkIn meetings", FAILED, ""));
        childData.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        childData.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        tempData.add(DataModel.multipleMessage("Checked in successfully", CONFLICT, childData));

        return tempData;
    }
}