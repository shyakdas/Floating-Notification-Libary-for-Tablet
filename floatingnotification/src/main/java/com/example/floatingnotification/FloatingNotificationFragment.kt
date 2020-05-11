package com.example.floatingnotification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.floatingnotification.adapter.NotificationITemAdapter
import com.example.floatingnotification.animator.SlideInOutRightItemAnimator
import com.example.floatingnotification.listener.OnCloseListener
import com.example.floatingnotification.models.NotificationItem


class FloatingNotificationFragment : Fragment(), OnCloseListener {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mNotificationITemAdapter: NotificationITemAdapter
    private lateinit var notificationItemList: ArrayList<NotificationItem<*>>
    private lateinit var floatNotificationViewModel: FloatNotificationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.floating_notifications_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initList(view)
        initAdapter()
        initNotificationData()
    }

    private fun initViewModel() {
        floatNotificationViewModel = ViewModelProvider(activity!!, defaultViewModelProviderFactory)
                .get(FloatNotificationViewModel::class.java)
    }

    private fun initList(view: View) {
        mRecyclerView = view.findViewById(R.id.recycler_view)
        notificationItemList = ArrayList()
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = SlideInOutRightItemAnimator(mRecyclerView)
        mNotificationITemAdapter = NotificationITemAdapter(context!!, notificationItemList, this)
        mRecyclerView.adapter = mNotificationITemAdapter
    }

    private fun initNotificationData() {
        activity?.let {
            floatNotificationViewModel.modelMutableLiveData.observe(it, Observer { notificationItem ->
                if (notificationItem != null) {
                    notificationItemList.add(notificationItem)
                    mNotificationITemAdapter.notifyItemInserted(notificationItemList.size - 1)
                }
            })
        }
    }

    override fun onClose(position: Int) {
        if (position != -1 && position < notificationItemList.size) {
            val notificationItem: NotificationItem<*> = notificationItemList[position]
            notificationItemList.remove(notificationItem)
            mNotificationITemAdapter.notifyItemRemoved(position)
            floatNotificationViewModel.onClose()
        }
    }
}