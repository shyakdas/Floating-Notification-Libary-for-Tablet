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
import com.example.floatingnotification.adapter.SubNotificationAdapter;
import com.example.floatingnotification.animator.SlideInOutRightItemAnimator;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.NotificationItem;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.FAILED;
import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class MultipleNotificationViewHolder extends NotificationViewHolder {

    private TextView mDetails;
    private View mExpandBorderView;
    private int originalHeight = 0;
    private boolean isViewExpanded = false;
    private RecyclerView mExpandRecyclerView;
    private boolean mIsViewExpanded = false;
    private SubNotificationAdapter mSubNotificationAdapter;
    private Context mContext;
    private ArrayList<NotificationItem> tempData;

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener, Context context) {
        super(itemView, listener);
        this.mContext = context;
        mDetails = itemView.findViewById(R.id.detail_text);
        mExpandRecyclerView = itemView.findViewById(R.id.recycler_view);
        mExpandBorderView = itemView.findViewById(R.id.divider3);
    }

    @Override
    public void bind(NotificationItem notificationItem) {
        super.bind(notificationItem);
        if (!isViewExpanded) {
            visibilityGone();
        }
        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();
            }
        });
    }

    private void showDetails() {
        // Stop the timer, View will not auto cancel
        NotificationViewHolder.stopTimer();
        // If the originalHeight is 0 then find the height of the View being used
        // This would be the height of the cardView
        if (originalHeight == 0) {
            originalHeight = itemView.getHeight();
        }
        if (!mIsViewExpanded) {
            expandedChildView();
        } else {
            collapsedChildView();
        }
    }

    private void expandedChildView() {
        childAdapter();
        mExpandRecyclerView.setVisibility(View.VISIBLE);
        mExpandBorderView.setVisibility(View.VISIBLE);
        // Set card layout margin in bottom
        mExpandRecyclerView.setEnabled(true);
        mIsViewExpanded = true;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 2.0));
        viewValueAnimator(valueAnimator);
    }

    private void collapsedChildView() {
        mIsViewExpanded = false;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 2.0), originalHeight);
        Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out
        a.setDuration(200);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                visibilityGone();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mExpandRecyclerView.startAnimation(a);
        viewValueAnimator(valueAnimator);
    }

    private void viewValueAnimator(ValueAnimator valueAnimator) {
        valueAnimator.setDuration(200);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                itemView.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                itemView.requestLayout();
            }
        });
        valueAnimator.start();
    }

    private void visibilityGone() {
        mExpandRecyclerView.setVisibility(View.GONE);
        mExpandBorderView.setVisibility(View.GONE);
        mExpandRecyclerView.setEnabled(false);
    }

    private void childAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mExpandRecyclerView.setLayoutManager(linearLayoutManager);
        mExpandRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mExpandRecyclerView));
        mSubNotificationAdapter = new SubNotificationAdapter(getTempData());
        mExpandRecyclerView.setAdapter(mSubNotificationAdapter);
    }

    private ArrayList<NotificationItem> getTempData() {
        tempData = new ArrayList<>();
        tempData.add(NotificationItem.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        tempData.add(NotificationItem.singleMessage("Failed to checkIn meetings", FAILED, ""));
        tempData.add(NotificationItem.singleMessage("4 Meetings have been checked in successfully", SUCCESS, ""));
        tempData.add(NotificationItem.singleMessage("Failed to checkIn meetings", FAILED, ""));
        return tempData;
    }
}