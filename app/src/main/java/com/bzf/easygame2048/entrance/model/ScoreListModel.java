package com.bzf.easygame2048.entrance.model;

/**
 * com.bzf.easygame2048.entrance.model
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public interface ScoreListModel {

    /**
     * 从服务器获取昨日榜单
     */
    void getScoreList(ScoreListModelImpl.ScoreListListener listenter);
}
