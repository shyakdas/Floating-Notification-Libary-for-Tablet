package com.example.floatingnotification.viewholders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
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

public class MultipleNotificationViewHolder extends NotificationViewHolder {

    private TextView mDetails;
    private View mExpandBorderView;
    private int originalHeight = 0;
    private boolean isViewExpanded = false;
    private RecyclerView mExpandRecyclerView;
    private boolean mIsViewExpanded = false;
    private SubNotificationAdapter mSubNotificationAdapter;
    private Context mContext;

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener, Context context) {
        super(itemView, listener);
        this.mContext = context;
        mDetails = itemView.findViewById(R.id.detail_text);
        mExpandRecyclerView = itemView.findViewById(R.id.recycler_view);
        mExpandBorderView = itemView.findViewById(R.id.divider3);
    }

    @Override
    public void bind(final NotificationItem notificationItem) {
        super.bind(notificationItem);
        if (!isViewExpanded) {
            visibilityGone();
        }
        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails(notificationItem);
            }
        });
    }

    private void showDetails(NotificationItem<ArrayList<NotificationItem<String>>> notificationItem) {
        // Stop the timer, View will not auto cancel
        NotificationViewHolder.stopTimer();
        // If the originalHeight is 0 then find the height of the View being used
        // This would be the height of the cardView
        if (originalHeight == 0) {
            originalHeight = itemView.getHeight();
        }
        if (!mIsViewExpanded) {
            expandedChildView(notificationItem);
        } else {
            collapsedChildView();
        }
    }

    private void expandedChildView(NotificationItem<ArrayList<NotificationItem<String>>> item) {
        childAdapter(item.getData());
        mExpandRecyclerView.setVisibility(View.VISIBLE);
        mExpandBorderView.setVisibility(View.VISIBLE);
        // Set card layout margin in bottom
        mExpandRecyclerView.setEnabled(true);
        mIsViewExpanded = true;
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 2.0));
//        viewValueAnimator(valueAnimator);
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

    private void childAdapter(ArrayList<NotificationItem<String>> items) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mExpandRecyclerView.setLayoutManager(linearLayoutManager);
        mExpandRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mExpandRecyclerView));
        mSubNotificationAdapter = new SubNotificationAdapter(items);
        mExpandRecyclerView.setAdapter(mSubNotificationAdapter);
        RecyclerView.OnItemTouchListener mScrollTouchListener = new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, MotionEvent e) {
                int action = e.getAction();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        rv.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        };

        mExpandRecyclerView.addOnItemTouchListener(mScrollTouchListener);
    }
}