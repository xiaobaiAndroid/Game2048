package com.bzf.easygame2048.entrance.widget;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.bean.User;
import com.bzf.easygame2048.entrance.presenter.ScoreListPresenterImpl;
import com.bzf.easygame2048.entrance.view.ScoreListView;
import com.bzf.easygame2048.main.widget.MainActivity;
import com.bzf.easygame2048.pk.widget.NewOrJoinActivity;

import java.util.List;

public class EntranceActivity extends BaseActivity implements ScoreListView {

    private RecyclerView mRV_Sort;
    private Button mB_login;
    private Button mB_tourist;
    private Button mB_pk;

    private ScoreListPresenterImpl mPresenter;
    private AlertDialog mDialog;
    private ScoreSortRecyclerAdapter mScoreSortRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        mPresenter.getScoreSortList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRV_Sort.setLayoutManager(layoutManager);
        mRV_Sort.setHasFixedSize(true);
        mRV_Sort.setItemAnimator(new DefaultItemAnimator());
    }

    private void initListener() {
        mB_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mB_tourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mB_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, NewOrJoinActivity.class));
            }
        });

    }

    private void initView() {
        mRV_Sort = getView(R.id.lv_sort);
        mB_login = getView(R.id.bt_login);
        mB_tourist = getView(R.id.bt_tourist);
        mB_pk = getView(R.id.bt_pk);
        mPresenter = new ScoreListPresenterImpl(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitAppDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitAppDialog() {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(this)
                    .setMessage("确定要退出游戏吗?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mDialog.dismiss();
                            GameApplication.getIns().exitApp(EntranceActivity.this);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mDialog.dismiss();
                        }
                    })
                    .create();
        }
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //退出应用
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void addScoreList(List<User> list) {
        if (mScoreSortRecyclerAdapter == null) {
            mScoreSortRecyclerAdapter = new ScoreSortRecyclerAdapter(list);
            mRV_Sort.setAdapter(mScoreSortRecyclerAdapter);
        } else {
            mScoreSortRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }
}

