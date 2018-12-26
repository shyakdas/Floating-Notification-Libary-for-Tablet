package com.example.shyakdas.recycleranimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickItemListener {

    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private ArrayList<DataModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
        mDataAdapter = new DataAdapter(this, dataModelList(), this);
        mRecyclerView.setAdapter(mDataAdapter);
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(new DataModel("1", "First Item"));
                mDataAdapter.notifyItemInserted(mList.size() - 1);
            }
        }, 1000);

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(new DataModel("2", "Second Item"));
                mDataAdapter.notifyItemInserted(mList.size() - 1);
            }
        }, 2000);

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(new DataModel("3", "Third Item"));
                mDataAdapter.notifyItemInserted(mList.size() - 1);
            }
        }, 3000);
    }

    private ArrayList<DataModel> dataModelList() {
        mList = new ArrayList<>();
        mList.add(new DataModel("0", "Zero Item"));
        return mList;
    }

    @Override
    public void onItemClick(int position) {
        mDataAdapter.remove(position);
    }
}