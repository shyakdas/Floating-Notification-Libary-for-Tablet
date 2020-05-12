package com.example.floatingnotification.models

import com.example.floatingnotification.R
import com.example.floatingnotification.utils.FloatingNotificationConstants

class NotificationItem<T> protected constructor(val containerType: Int, val message: String?,
                                                @field:FloatingNotificationConstants.Companion.MessageType
                                                val type: String) {
    var data: T? = null
        private set

    protected constructor(containerType: Int, message: String?, type: String, data: T) : this(containerType, message, type) {
        this.data = data
    }

    fun setData(data: T) {
        this.data = data
    }

    val resId: Int
        get() = if (type.equals(FloatingNotificationConstants.SUCCESS, ignoreCase = true))
            R.drawable.notification_success else if (type.equals(FloatingNotificationConstants.FAILED,
                        ignoreCase = true)) R.drawable.notification_failed else R.drawable.notification_conflict

    val subResId: Int
        get() = if (type.equals(FloatingNotificationConstants.SUCCESS, ignoreCase = true))
            R.drawable.sub_notification_success else if (type.equals(FloatingNotificationConstants.FAILED,
                        ignoreCase = true)) R.drawable.notification_failed else R.drawable.sub_notification_conflict

    companion object {
        fun <T> singleMessage(message: String?,
                              @FloatingNotificationConstants.Companion.MessageType type: String,
                              data: T): NotificationItem<T> {
            return NotificationItem(FloatingNotificationConstants.SINGLE, message, type, data)
        }

        fun <T> multipleMessage(message: String?,
                                @FloatingNotificationConstants.Companion.MessageType type: String, dataModels: T): NotificationItem<T> {
            return NotificationItem(FloatingNotificationConstants.MULTIPLE, message, type, dataModels)
        }
    }
}