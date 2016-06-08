package com.qiaobin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhiqiang on 2015/8/5 0005.
 */
public class FightUser extends BmobObject {
    private String userName;//当前平台注册用户名
    private String gameName;//那款游戏
    private String whereQuFu;//游戏区服
    private String gameUserName;//游戏ID名--对方用户名
    private String ToFighttime;//约战时间
    private String gameResult;//约战结果
    private String state;//是否开始 0 未到时间未开始；1到时间自动开始 ；3 因其他原因未开始，自动打款后讨回请求对方同意

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getWhereQuFu() {
        return whereQuFu;
    }

    public void setWhereQuFu(String whereQuFu) {
        this.whereQuFu = whereQuFu;
    }

    public String getGameUserName() {
        return gameUserName;
    }

    public void setGameUserName(String gameUserName) {
        this.gameUserName = gameUserName;
    }

    public String getToFighttime() {
        return ToFighttime;
    }

    public void setToFighttime(String toFighttime) {
        ToFighttime = toFighttime;
    }

    public String getGameResult() {
        return gameResult;
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
