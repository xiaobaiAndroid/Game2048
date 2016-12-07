package com.bzf.easygame2048.bean;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class User {

    private String name; //用户名
    private String headPath;//头像路径
    private String userId;//账号
    private int hightScore;//历史最高分
    private int age;//年龄

    public User() {
    }

    public User(String name, int hightScore) {
        this.name = name;
        this.hightScore = hightScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHightScore() {
        return hightScore;
    }

    public void setHightScore(int hightScore) {
        this.hightScore = hightScore;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
