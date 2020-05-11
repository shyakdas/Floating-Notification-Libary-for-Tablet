package com.example.floatingnotification.utils

import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

class FloatingNotificationConstants {
    companion object {
        const val SUCCESS = "notification_success"
        const val FAILED = "notification_failed"
        const val CONFLICT = "notification_conflict"
        const val SUCCESS_INT = 101
        const val FAILED_INT = 102
        const val CONFLICT_INT = 103
        const val SINGLE = 104
        const val MULTIPLE = 103

        @StringDef(SUCCESS, FAILED, CONFLICT)
        @Retention(RetentionPolicy.SOURCE)
        annotation class MessageType
    }
}