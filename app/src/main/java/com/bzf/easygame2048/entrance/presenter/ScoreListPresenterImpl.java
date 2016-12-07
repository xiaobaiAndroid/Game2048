package com.bzf.easygame2048.entrance.presenter;

import com.bzf.easygame2048.bean.User;
import com.bzf.easygame2048.entrance.model.ScoreListModel;
import com.bzf.easygame2048.entrance.model.ScoreListModelImpl;
import com.bzf.easygame2048.entrance.view.ScoreListView;

import java.util.List;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class ScoreListPresenterImpl implements ScoreListPresenter,ScoreListModelImpl.ScoreListListener {

    private ScoreListView mScoreSortListView;
    private ScoreListModel mScoreListModel;

    public ScoreListPresenterImpl(ScoreListView view){
        mScoreSortListView = view;
        mScoreListModel = new ScoreListModelImpl();
    }

    @Override
    public void getScoreSortList() {
        mScoreSortListView.showProgress();
        mScoreListModel.getScoreList(this);
    }

    @Override
    public void success(List<User> list) {
        if(mScoreSortListView!=null){
            mScoreSortListView.closeProgress();
            mScoreSortListView.addScoreList(list);
        }
    }

    @Override
    public void fail(String msg, Throwable e) {
        if(mScoreSortListView!=null){
            mScoreSortListView.closeProgress();
            mScoreSortListView.showLoadFailMsg();
        }
    }

//    /**获取昨日榜单数据*/
//    public List<User> getScoreSortList(){
//        List<User> mList = new ArrayList<User>();
//        User user;
//        for(int i=0; i<10; i++){
//            user = new User("张三1",4096-i);
//            mList.add(user);
//        }
//        return mList;
//    }

}
