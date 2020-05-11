package com.example.floatingnotification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.floatingnotification.R
import com.example.floatingnotification.models.NotificationItem
import com.example.floatingnotification.viewholders.SubNotificationHolder
import java.util.*

class SubNotificationAdapter(private val notificationItemArrayList: ArrayList<NotificationItem<String>>)
    : RecyclerView.Adapter<SubNotificationHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SubNotificationHolder {
        return SubNotificationHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.notification_child_item, viewGroup, false))
    }

    override fun onBindViewHolder(notificationItemHolder: SubNotificationHolder, position: Int) {
        notificationItemHolder.bindView(notificationItemArrayList[position])
    }

    override fun getItemCount(): Int {
        return notificationItemArrayList.size
    }
}