package com.qiaobin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/8.
 */
public class AcceptOrder extends BmobObject {
    private String userName;//发单人
    private String userNameGameNick;
    private String acceptUserName;//接单人名称，接单后可以决定是否刷新
    private String acceptNameGameNick;//接单人游戏昵称
    private String gameName;//发单人所选的游戏
    private String whereQuFu;//游戏区服---需包含大区小区
    private String sendOrderQQ;//发单人QQ
    private String accpetOrderQQ;//接单人QQ
    private String sendOrderPhone;//发单人电话
    private String acceptOrderPhone;//接单人电话
    private String ToFighttime;//发单人发布的挑战时间
    private int state;//是否已经被接单 0 表示未被接单  1表示 已经被接单
    private String gameResult;//双方pk结果 这里应该是用不着，但是还是给一个
}
