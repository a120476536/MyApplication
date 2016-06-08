package com.qiaobin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhiqiang on 2015/8/5 0005.
 */
public class PersonObject extends BmobObject {
    public  String userName;
    public  String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
