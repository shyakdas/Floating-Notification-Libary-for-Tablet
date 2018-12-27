package com.example.shyakdas.recycleranimation;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity implements OnClickItemListener {

    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private ArrayList<DataModel> mList;
    private ArrayList<DataModel> mainList;
    private int TOTAL_CAPACITY = 3;
    private Queue<DataModel> dataModelQueue;
    private int position = 0;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mList = new ArrayList<>();
        mainList = new ArrayList<>();
        dataModelQueue = new LinkedList<>();
        mainListData();
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
        mDataAdapter = new DataAdapter(this, mList, this);
        mRecyclerView.setAdapter(mDataAdapter);
        goToQueue();
    }

    @Override
    public void onItemClick(int position) {
        onItemRemove(position);
    }

    public void mainListData() {
        mainList.add(new DataModel("0", "A", "success"));
        mainList.add(new DataModel("1", "B", "success"));
        mainList.add(new DataModel("2", "C", "Conflict"));
        mainList.add(new DataModel("3", "D", "success"));
        mainList.add(new DataModel("4", "E", "Conflict"));
        mainList.add(new DataModel("5", "F", "success"));
        mainList.add(new DataModel("6", "G", "Failed"));
    }


    public void goToQueue() {

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (position < mainList.size()) {
                    addMessage(mainList.get(position));
                    position++;
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void onItemRemove(int pos) {
        if (pos > -1) {
            mList.remove(pos);
            mDataAdapter.notifyItemRemoved(pos);
        }

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!dataModelQueue.isEmpty()) {
                    mList.add(dataModelQueue.poll());
                    mDataAdapter.notifyItemInserted(mList.size() - 1);
                }
            }
        }, 2000);

    }

    public void addMessage(final DataModel dataModel) {
        if (mList.size() < TOTAL_CAPACITY) {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mList.add(dataModel);
                    mDataAdapter.notifyItemInserted(mList.size() - 1);
                }
            }, 1000);
        } else {
            dataModelQueue.add(dataModel);
        }
    }
}