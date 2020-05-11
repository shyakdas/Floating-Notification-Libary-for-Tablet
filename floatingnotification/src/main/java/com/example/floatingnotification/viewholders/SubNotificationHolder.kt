package com.example.floatingnotification.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.floatingnotification.models.NotificationItem
import kotlinx.android.synthetic.main.notification_child_item.view.*

class SubNotificationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(notificationItem: NotificationItem<String>) {
        itemView.message_text.text = notificationItem.message
        itemView.imageView.setImageResource(notificationItem.subResId)
    }
}