package com.example.floatingnotification.viewholders

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.floatingnotification.adapter.SubNotificationAdapter
import com.example.floatingnotification.animator.SlideInOutRightItemAnimator
import com.example.floatingnotification.listener.OnCloseListener
import com.example.floatingnotification.models.NotificationItem
import kotlinx.android.synthetic.main.multiple_notification_item.view.*
import java.util.*

class MultipleNotificationViewHolder(itemView: View, onCloseListener: OnCloseListener, var context: Context)
    : NotificationViewHolder(itemView, onCloseListener) {
    private var mSubNotificationAdapter: SubNotificationAdapter? = null
    private var originalHeight = 0
    private var mIsViewExpanded = false

    override fun bind(notificationItem: NotificationItem<*>) {
        super.bind(notificationItem)
        visibilityGone()
        itemView.detail_text.setOnClickListener { showDetails(notificationItem) }
    }

    private fun showDetails(notificationItem: NotificationItem<*>) {
        stopTimer()
        if (originalHeight == 0) {
            originalHeight = itemView.height
        }
        if (!mIsViewExpanded) {
            expandedChildView(notificationItem)
        } else {
            collapsedChildView()
        }
    }

    private fun expandedChildView(item: NotificationItem<*>) {
        childAdapter(item.data!!)
        itemView.recycler_view.visibility = View.VISIBLE
        itemView.divider3.visibility = View.VISIBLE
        itemView.recycler_view.isEnabled = true
        mIsViewExpanded = true
    }

    private fun collapsedChildView() {
        mIsViewExpanded = false
        val a: Animation = AlphaAnimation(1.00f, 0.00f) // Fade out
        a.duration = 200
        a.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                visibilityGone()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        itemView.recycler_view.startAnimation(a)
    }

    private fun visibilityGone() {
        itemView.recycler_view.visibility = View.GONE
        itemView.divider3.visibility = View.GONE
        itemView.recycler_view.isEnabled = false
    }

    private fun childAdapter(items: Any) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        itemView.recycler_view.layoutManager = linearLayoutManager
        itemView.recycler_view.itemAnimator = SlideInOutRightItemAnimator(itemView.recycler_view)
        mSubNotificationAdapter = SubNotificationAdapter(items as ArrayList<NotificationItem<String>>)
        itemView.recycler_view.adapter = mSubNotificationAdapter
        val mScrollTouchListener: OnItemTouchListener = object : OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        }
        itemView.recycler_view.addOnItemTouchListener(mScrollTouchListener)
    }
}