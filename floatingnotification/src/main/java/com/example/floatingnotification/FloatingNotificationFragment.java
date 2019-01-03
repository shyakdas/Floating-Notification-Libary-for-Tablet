package com.example.floatingnotification;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.floatingnotification.adapter.NotificationITemAdapter;
import com.example.floatingnotification.animator.SlideInOutRightItemAnimator;
import com.example.floatingnotification.listener.OnCloseListener;
import com.example.floatingnotification.models.NotificationItem;

import java.util.ArrayList;

public class FloatingNotificationFragment extends Fragment implements OnCloseListener {

    private RecyclerView mRecyclerView;
    private NotificationITemAdapter mNotificationITemAdapter;
    private ArrayList<NotificationItem> notificationItemList;
    private FloatNotificationViewModel floatNotificationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.floating_notifications_layout, container, false);
        return view;
    }


    private void initList(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        notificationItemList = new ArrayList<>();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
        mNotificationITemAdapter = new NotificationITemAdapter(getContext(), notificationItemList, this);
        mRecyclerView.setAdapter(mNotificationITemAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            floatNotificationViewModel = ViewModelProviders.of(getActivity()).get(FloatNotificationViewModel.class);
            initList(view);
            initAdapter();
            initNotificationData();
        }
    }


    private void initNotificationData() {
        floatNotificationViewModel.getModelMutableLiveData().observe(this, new Observer<NotificationItem>() {
            @Override
            public void onChanged(@Nullable NotificationItem notificationItem) {
                if (notificationItem != null) {
                    notificationItemList.add(notificationItem);
                    mNotificationITemAdapter.notifyItemInserted(notificationItemList.size() - 1);
                }
            }
        });
    }

    @Override
    public void onClose(int position) {
        if (position != -1 && position < notificationItemList.size()) {
            NotificationItem notificationItem = notificationItemList.get(position);
            notificationItemList.remove(notificationItem);
            mNotificationITemAdapter.notifyItemRemoved(position);
            floatNotificationViewModel.onClose();
        }
    }
}