package com.qiaobin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhiqiang on 2015/8/5 0005.
 */
public class FightUser extends BmobObject {
    private String userName;//��ǰƽ̨ע���û���
    private String gameName;//�ǿ���Ϸ
    private String whereQuFu;//��Ϸ����
    private String gameUserName;//��ϷID��--�Է��û���
    private String ToFighttime;//Լսʱ��
    private String gameResult;//Լս���
    private String state;//�Ƿ�ʼ 0 δ��ʱ��δ��ʼ��1��ʱ���Զ���ʼ ��3 ������ԭ��δ��ʼ���Զ������ֻ�����Է�ͬ��

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
