package com.bzf.easygame2048.entrance.view;

import com.bzf.easygame2048.bean.User;

import java.util.List;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public interface ScoreListView {

    void addScoreList(List<User>list);
    void showProgress();
    void closeProgress();
    void showLoadFailMsg();

}
