package com.qiaobin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/8.
 * 发单
 */
public class SendOrder extends BmobObject {
    public String userName;//发单人
    public String userNameGameNick;//发单人游戏昵称
    public double userNameSetPKMoney;//发单人设置的pk金额，----不能超过 已有的总金额
    public double userNameAllMoney;//发单人现有身价，---现有总金额
    public String acceptUserName;//接单人名称，接单后可以决定是否刷新
    public double acceptUserNamePKMoney;//接单人接受发单人发布的PK金额，----自己账户需预留匹配金额---也就是总金额需要》=PK金额
    public double acceptUserNameAllMoney;//接单人现有总金额
    public String acceptNameGameNick;//接单人游戏昵称
    public String gameName;//发单人所选的游戏
    public String whereQuFu;//游戏区服---需包含大区小区
    public String sendOrderQQ;//发单人QQ 必填
    public String accpetOrderQQ;//接单人QQ 必填
    public String sendOrderPhone;//发单人电话 --非必填
    public String acceptOrderPhone;//接单人电话 --非必填
    public String ToFighttime;//发单人发布的挑战时间
    public String userNameResultImage;//发单方上传自身游戏结果，附带对方结果
    public String acceptUserNameResultImage;//接单方上传自身游戏结果 ，附带对方结果
    public String userNameResult;//发单人PK结果 （0 表示 输掉  |   1 表示赢了）
    public String acceptUserNameResult;//接单人PK结果
    public String gameResult;//双方pk结果 这里应该是用不着，但是还是给一个
    public int state;
    //双方若平局则可选择从新开局，不用重新下单，之后上传自身结果就可当期订单上传结果就好
    //（0表示未被接单）
    //（1表示已经被接单）
    //（2表示订单已完成）
    //（3表示订单有异常，，双方提交答案不一致，进入客服评审阶段手动审单，涉及惩罚 人工执行数据库更新操作）
    //（10 表示订单过期发单未接）
}
