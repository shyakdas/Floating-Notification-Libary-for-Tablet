package com.example.floatingnotification.viewholders;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.floatingnotification.R;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.DataModel;

public class MultipleNotificationViewHolder extends NotificationViewHolder {

    private TextView mDetails;
    private View mExpandBoderView;
    private int originalHeight = 0;
    private boolean isViewExpanded = false;
    private ConstraintLayout mExpandLayout;
    private boolean mIsViewExpanded = false;

    public MultipleNotificationViewHolder(@NonNull View itemView, OnCloseListener listener) {
        super(itemView, listener);
        mDetails = itemView.findViewById(R.id.detail_text);
        mExpandLayout = itemView.findViewById(R.id.notification_item);
        mExpandBoderView = itemView.findViewById(R.id.expand_view);
    }

    @Override
    public void bind(DataModel dataModel) {
        super.bind(dataModel);

        if (!isViewExpanded) {
            mExpandLayout.setVisibility(View.GONE);
            mExpandBoderView.setVisibility(View.GONE);
            mExpandLayout.setEnabled(false);
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
            mExpandLayout.setVisibility(View.VISIBLE);
            mExpandBoderView.setVisibility(View.VISIBLE);
            mExpandLayout.setEnabled(true);
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
                    mExpandLayout.setVisibility(View.INVISIBLE);
                    mExpandBoderView.setVisibility(View.INVISIBLE);
                    mExpandLayout.setEnabled(false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mExpandLayout.startAnimation(a);
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
}