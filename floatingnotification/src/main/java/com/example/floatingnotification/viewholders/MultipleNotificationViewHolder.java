package com.example.floatingnotification.viewholders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.adapter.NotificationChildAdapter;
import com.example.floatingnotification.animator.SlideInOutRightItemAnimator;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;

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
    private NotificationChildAdapter mNotificationChildAdapter;
    private Context mContext;
    private ArrayList<DataModel> tempData;
    private CardView mCardLayout;

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener, Context context) {
        super(itemView, listener);
        this.mContext = context;
        mDetails = itemView.findViewById(R.id.detail_text);
        mExpandRecyclerView = itemView.findViewById(R.id.recycler_view);
        mExpandBorderView = itemView.findViewById(R.id.expand_view);
        mCardLayout = itemView.findViewById(R.id.content_layout);
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
            // Set card layout margin in bottom
            setLayoutParams(0);
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
                    setLayoutParams(5);
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

    public void setLayoutParams(int bottomMargin) {
        ViewGroup.MarginLayoutParams layoutParams =
                (ViewGroup.MarginLayoutParams) mCardLayout.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, bottomMargin);
        mCardLayout.requestLayout();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mExpandRecyclerView.setLayoutManager(linearLayoutManager);
        mExpandRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mExpandRecyclerView));
        mNotificationChildAdapter = new NotificationChildAdapter(getTempData());
        mExpandRecyclerView.setAdapter(mNotificationChildAdapter);
    }

    public ArrayList<DataModel> getTempData() {
        tempData = new ArrayList<>();
        tempData.add(DataModel.singleMessage("Selected user checked-in successfully to Meeting ID #34", SUCCESS, ""));
        tempData.add(DataModel.singleMessage("Failed to checkIn meetings", FAILED, ""));
        tempData.add(DataModel.singleMessage("4 Meetings have been checked in successfully", SUCCESS, ""));
        tempData.add(DataModel.singleMessage("Failed to checkIn meetings", FAILED, ""));
        return tempData;
    }
}