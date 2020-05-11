package com.example.floatingnotification.models

import com.example.floatingnotification.utils.FloatingNotificationConstants
import java.util.*

class MessageParser {
    companion object {
        fun addSingleMessage(message: String,
                             @FloatingNotificationConstants.Companion.MessageType type: String?): NotificationItem<String> {
            return NotificationItem.singleMessage(message, type, message)
        }

        fun addMultipleMessage(data: ConflictData, message: String): NotificationItem<ArrayList<NotificationItem<String>>> {
            @FloatingNotificationConstants.Companion.MessageType var type: String? = null
            val notificationItems = ArrayList<NotificationItem<String>>()
            var isSuccessAvailable = false
            if (data.success_messages!!.isNotEmpty()) {
                type = FloatingNotificationConstants.SUCCESS
                isSuccessAvailable = true
                for (singleMessage in data.success_messages!!) {
                    notificationItems.add(NotificationItem.singleMessage(singleMessage,
                            FloatingNotificationConstants.SUCCESS, message))
                }
            }
            if (data.error_messages!!.isNotEmpty()) {
                val errors = ArrayList<NotificationItem<String>>()
                type = if (isSuccessAvailable) FloatingNotificationConstants.CONFLICT else FloatingNotificationConstants.FAILED
                for (singleMessage in data.error_messages!!) {
                    if (isSuccessAvailable) errors.add(NotificationItem.singleMessage(singleMessage,
                            FloatingNotificationConstants.CONFLICT, message)) else {
                        errors.add(NotificationItem.singleMessage(singleMessage,
                                FloatingNotificationConstants.FAILED, message))
                    }
                }
                notificationItems.addAll(0, errors)
            }
            return NotificationItem.multipleMessage(message, type, notificationItems)
        }
    }
}