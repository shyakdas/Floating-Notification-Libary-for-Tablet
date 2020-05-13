package com.example.shyakdas.recycleranimation

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.floatingnotification.FloatNotificationViewModel
import com.example.floatingnotification.models.NotificationItem
import com.example.floatingnotification.models.NotificationItem.Companion.multipleMessage
import com.example.floatingnotification.models.NotificationItem.Companion.singleMessage
import com.example.floatingnotification.utils.FloatingNotificationConstants
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivityKt : AppCompatActivity(), View.OnClickListener {
    private var notificationItems: ArrayList<NotificationItem<*>>? = null
    private var position = 0
    private var viewModel: FloatNotificationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel();
        initListener()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(FloatNotificationViewModel::class.java)
    }

    private fun initListener() {
        view_anim_button.setOnClickListener(this)
    }

    private fun initData() {
        notificationItems = ArrayList()
        val childData = ArrayList<NotificationItem<String>>()
        notificationItems!!.add(singleMessage("Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        notificationItems!!.add(singleMessage("Failed to checkIn meetings", FloatingNotificationConstants.FAILED, ""))
        notificationItems!!.add(multipleMessage("Checked in successfully", FloatingNotificationConstants.CONFLICT, childData))
        childData.add(singleMessage("1. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("2. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("3. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("4. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("5. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("6. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("7. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("8. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.SUCCESS, ""))
        childData.add(singleMessage("9. Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("10.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("11.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("12.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("13.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("14.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("15.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("16.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("17.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("18.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("19.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("20.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("21.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("22.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("23.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("24.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("25.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("26.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("27.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("28.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("29.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("30.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("31.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("32.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        childData.add(singleMessage("33.Selected user checked-in successfully to Meeting ID #34", FloatingNotificationConstants.FAILED, ""))
        notificationItems!!.add(singleMessage("4 Meetings have been checked in successfully", FloatingNotificationConstants.SUCCESS, ""))
        notificationItems!!.add(singleMessage("Failed to checkIn meetings", FloatingNotificationConstants.FAILED, ""))
        position = 0
    }

    private fun goToQueue() {
        initData()
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (position < notificationItems!!.size) {
                    viewModel!!.addNotifications(notificationItems!![position])
                }
                position++
            }

            override fun onFinish() {}
        }.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.view_anim_button -> goToQueue()
        }
    }
}