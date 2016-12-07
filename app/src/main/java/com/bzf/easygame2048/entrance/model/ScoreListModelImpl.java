package com.bzf.easygame2048.entrance.model;

import com.bzf.easygame2048.bean.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * com.bzf.easygame2048.entrance.model
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class ScoreListModelImpl implements ScoreListModel {


    @Override
    public void getScoreList(final ScoreListListener listenter) {
//        Retrofit retrofit= new Retrofit.Builder()
//                .baseUrl(HttpUtils.SCORELISTURL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        retrofit.create(ScoreListPort.class)
//                .getScoreListPort()
//                .subscribeOn(Schedulers.io())
//                .map(new Func1<ResponseBody, List<User>>() {
//                    @Override
//                    public List<User> call(ResponseBody responseBody) {
//                        return null;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<User>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        listenter.fail("request is failed",  e);
//                    }
//
//                    @Override
//                    public void onNext(List<User> users) {
//                        listenter.success(users);
//                    }
//                });
    }

    private interface ScoreListPort{

        @GET("/scoreList")
        public Observable<ResponseBody> getScoreListPort();
    };

    /**
     * 服务器返回监听器
     */
    public interface ScoreListListener{
        void success(List<User> list);
        void fail(String msg, Throwable e);
    }

}