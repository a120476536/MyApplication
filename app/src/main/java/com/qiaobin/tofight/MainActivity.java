package com.qiaobin.tofight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import com.qiaobin.bean.FightUser;
import com.qiaobin.utils.SharedPreferenceUtil;
import com.qiaobin.utils.Utils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


public class MainActivity extends Activity {

    private  MainActivity mainActivity;
    private Button button_pk,button_to_figth;
    private SharedPreferenceUtil sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity  = MainActivity.this;
        sp =  SharedPreferenceUtil.getInstance(mainActivity);
        findView();
        initListener();
    }

    private void initListener() {
        button_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FightUser fightUser = new FightUser();
                BmobQuery<FightUser> bmobQuery = new BmobQuery<FightUser>();
                bmobQuery.setLimit(1);
                bmobQuery.addWhereEqualTo("userName", sp.getString("userName",""));
                bmobQuery.findObjects(mainActivity, new FindListener<FightUser>() {
                    @Override
                    public void onSuccess(List<FightUser> list) {
                        if (list.size()>0){
                            for (int i = 0; i<list.size();i++){
                                Utils.mLogError("==-->list "+list.get(i));
                            }
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });

                //这是查询2015年2月11之前的Person数据
//                bmobQuery.addWhereGreaterThan("createdAt", new BmobDate(date));
                bmobQuery.order("-userName,-createdAt");
                bmobQuery.findObjects(mainActivity, new FindListener<FightUser>() {
                    @Override
                    public void onSuccess(List<FightUser> list) {
                        toast(list.size() + "");
                        if (list.size() == 0) {

                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                Intent intent = new Intent(mainActivity, PkActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findView() {
        button_pk = (Button) findViewById(R.id.button_pk);
        button_to_figth = (Button) findViewById(R.id.button_to_figth);
    }

    private void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
