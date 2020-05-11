package com.example.floatingnotification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.floatingnotification.R
import com.example.floatingnotification.listener.OnCloseListener
import com.example.floatingnotification.models.NotificationItem
import com.example.floatingnotification.utils.FloatingNotificationConstants
import com.example.floatingnotification.viewholders.MultipleNotificationViewHolder
import com.example.floatingnotification.viewholders.NotificationViewHolder
import com.example.floatingnotification.viewholders.SingleNotificationViewHolder
import java.util.*

class NotificationITemAdapter(private val mContext: Context,
                              private val mDataList: ArrayList<NotificationItem<*>>,
                              private val listener: OnCloseListener)
    : RecyclerView.Adapter<NotificationViewHolder>() {

    private var lastPosition = -1
    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): NotificationViewHolder {
        return when (itemType) {
            FloatingNotificationConstants.SINGLE ->
                SingleNotificationViewHolder(LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.single_notification_item, viewGroup, false), listener)
            FloatingNotificationConstants.MULTIPLE ->
                MultipleNotificationViewHolder(LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.multiple_notification_item, viewGroup, false), listener, mContext)
            else -> SingleNotificationViewHolder(LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.single_notification_item, viewGroup, false), listener)
        }
    }

    override fun onBindViewHolder(notificationViewHolder: NotificationViewHolder, position: Int) {
        notificationViewHolder.bind(mDataList[position])
        setInAnimation(notificationViewHolder.itemView, position)
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].containerType
    }

    private fun setInAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(mContext, R.anim.layout_animation_slide_right)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}