package com.example.floatingnotification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floatingnotification.models.NotificationItem
import java.util.*

class FloatNotificationViewModelKt : ViewModel() {
    private val TOTAL_CAPACITY = 3
    private val notificationItemQueue: Queue<NotificationItem<*>>? = LinkedList()
    val modelMutableLiveData = MutableLiveData<NotificationItem<*>>()
    private var count = 0

    fun addNotifications(notificationItem: NotificationItem<*>?) {
        notificationItemQueue!!.add(notificationItem)
        if (count < TOTAL_CAPACITY) pushToList()
    }

    fun onClose() {
        count--
        pushToList()
    }

    private fun pushToList() {
        if (!notificationItemQueue!!.isEmpty()) {
            count++
            val firstModel = notificationItemQueue.poll()
            modelMutableLiveData.value = firstModel
        }
    }
}