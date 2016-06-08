package com.qiaobin.tofight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import com.qiaobin.bean.FightUser;
import com.qiaobin.bean.PersonObject;
import com.qiaobin.utils.SharedPreferenceUtil;
import com.qiaobin.utils.Utils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends Activity {
    private EditText editText_name,editText_pwd;
    private Button button_reg,button_login,button_forget_pwd;
    private LoginActivity lg;
    private SharedPreferenceUtil sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "3117451ff3400e14c5e091c4db7edab3");
        setContentView(R.layout.activity_login);
        lg  =LoginActivity.this;
        sp =  SharedPreferenceUtil.getInstance(lg);
        findView();
        initListener();
    }

    private void initListener() {
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lg,RegActivity.class);
                startActivity(intent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                final PersonObject PersonObject = new PersonObject();
                BmobQuery<PersonObject> bmobQuery = new BmobQuery<PersonObject>();
                bmobQuery.addWhereEqualTo("userName", editText_name.getText().toString());
                if (editText_name.getText()==null){
                    toast("you must write username");
                    return;
                }else if (editText_pwd.getText()==null){
                    toast("you must write pwd");
                    return;
                }
                bmobQuery.findObjects(lg, new FindListener<com.qiaobin.bean.PersonObject>() {
                    @Override
                    public void onSuccess(List<PersonObject> list) {
                        if (list.size() == 1) {
                            sp.saveString("userName",editText_name.getText().toString());
                            Intent intent = new Intent(lg, MainActivity.class);
                            startActivity(intent);
                        } else {
                            toast("userName or pwd is error ,so you can not to login ,plese check it ,ok? " );
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                        toast("the network is not power");
                    }
                });
            }
        });
        button_forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FightUser FightUser = new FightUser();
                FightUser.setUserName("qiaobin");
                FightUser.setGameName("ħ��");
                FightUser.setWhereQuFu("��������");
                FightUser.setGameUserName("����");
                FightUser.setToFighttime("2015-8-5 17:36:50");
                FightUser.setGameResult("��");
                FightUser.setState("0");
                BmobQuery<FightUser> bmFightUser = new BmobQuery<FightUser>();
                bmFightUser.addWhereEqualTo("userName","qiaobin");
                bmFightUser.findObjects(lg, new FindListener<com.qiaobin.bean.FightUser>() {
                    @Override
                    public void onSuccess(List<FightUser> list) {
                        if (list.size()==1){
                            //�Ѿ����ڲ�����ע��
                            toast("sql has it ,now can not to reg ");
                        }else{
                            FightUser.save(lg, new SaveListener() {
                                @Override
                                public void onSuccess() {
                                    toast("reg  result is success��now you can login ");
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                    toast("reg  loss");
                                }
                            });
                        }

                    }

                    @Override
                    public void onError(int i, String s) {
                        Utils.mLogError("==--> find loss "+s);
                        toast("find loss");
                    }
                });
//                bmFightUser.addWhereEqualTo("userName", editText_name.getText().toString());
//                bmFightUser.findObjects(lg, new FindListener<com.qiaobin.bean.FightUser>() {
//                    @Override
//                    public void onSuccess(List<FightUser> list) {
//                        if (list.size()==1){
//                            toast("yi jing  tian  jia  ");
//                        }else{
//                            FightUser.save(lg, new SaveListener() {
//                                @Override
//                                public void onSuccess() {
//                                    toast("success ");
//                                }
//
//                                @Override
//                                public void onFailure(int i, String s) {
//                                    toast("chuang jian  shi bai  ");
//                                }
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onError(int i, String s) {
//                        Utils.mLogError("==--> find loss "+s);
//                        toast("find loss");
//                    }
//                });
            }
        });

    }

    private void findView() {
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
        button_reg = (Button) findViewById(R.id.button_reg);
        button_login = (Button) findViewById(R.id.button_login);
        button_forget_pwd = (Button) findViewById(R.id.button_forget_pwd);
    }


    private void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
