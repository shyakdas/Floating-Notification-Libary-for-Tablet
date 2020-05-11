package com.example.floatingnotification.viewholders

import android.os.CountDownTimer
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.example.floatingnotification.listener.OnCloseListener
import com.example.floatingnotification.models.NotificationItem
import kotlinx.android.synthetic.main.multiple_notification_item.view.*

abstract class NotificationViewHolderKt(itemView: View, var onCloseListener: OnCloseListener)
    : RecyclerView.ViewHolder(itemView) {

    private var timer: CountDownTimer? = null

    open fun stopTimer() {
        timer?.cancel()
    }

    open fun startTimer() {
        timer = object : CountDownTimer(6000, 6000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                close()
            }
        }.start()
    }

    protected open fun close() {
        onCloseListener.onClose(adapterPosition)
    }

    @CallSuper
    open fun bind(notificationItem: NotificationItem<*>) {
        itemView.imageView.setImageResource(notificationItem.resId)
        itemView.message_text.text = notificationItem.message
        startTimer()
        itemView.close_notification.setOnClickListener {
            stopTimer()
            close()
        }
    }
}