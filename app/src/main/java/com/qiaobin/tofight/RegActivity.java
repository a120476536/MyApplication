package com.qiaobin.tofight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import com.qiaobin.bean.PersonObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class RegActivity extends Activity {

    private EditText editText_name,editText_pwd,editText_pwd_2,editText_telph,editText_id_card;
    private Button button_reg;
    private  RegActivity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        act = RegActivity.this;
        findView();
        initListener();
    }

    private void initListener() {
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_name.getText() == null) {
                    toast("username can not is null");
                }else if (editText_pwd.getText()==null){
                    toast("pwd can not is null");
                }else if (editText_pwd_2.getText()==null){
                    toast("pwd make sure can not is null");
                }/*else if (editText_pwd_2.getText()!=editText_pwd.getText()){
                        toast(" pwd is not same ");
                }*/else if (editText_telph.getText()==null){
                    toast("telph  can not is null");
                }else if (editText_id_card.getText()==null){
                    toast("telph  can not is null,if you forget pwd you can use it get pwd form our services");
                }else{
                    if (!editText_pwd.getText().toString().equals(editText_pwd_2.getText().toString())){
                        toast(" pwd is not same ");
                        return;
                    }
                    final PersonObject PersonObject = new PersonObject();
                    PersonObject.setUserName(editText_name.getText().toString());
                    PersonObject.setUserPwd(editText_pwd_2.getText().toString());
                    BmobQuery<PersonObject> bmobQuery = new BmobQuery<PersonObject>();
//                  bmobQuery.addQueryKeys("userName");
                    bmobQuery.addWhereEqualTo("userName", editText_name.getText().toString());
                    bmobQuery.findObjects(act, new FindListener<com.qiaobin.bean.PersonObject>() {
                        @Override
                        public void onSuccess(List<PersonObject> list) {
                            if (list.size() == 1) {
                                //已经存在不允许注册
                                toast("sql has it ,now can not to reg ");
                            } else {
                                PersonObject.save(act, new SaveListener() {
                                    @Override
                                    public void onSuccess() {
                                        // TODO Auto-generated method stub
                                        toast("reg  result is success，now you can login " + PersonObject.getObjectId());
                                        Intent intent = new Intent(act,LoginActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(int code, String msg) {
                                        // TODO Auto-generated method stub
                                        toast("result is lose ：" + msg);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(int i, String s) {
                            toast("the network is not power");
                        }
                    });
                }

            }
        });
    }

    private void findView() {
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
        editText_pwd_2 = (EditText) findViewById(R.id.editText_pwd_2);
        editText_telph = (EditText) findViewById(R.id.editText_telph);
        editText_id_card = (EditText) findViewById(R.id.editText_id_card);
        button_reg = (Button) findViewById(R.id.button_reg);
    }

    private void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
