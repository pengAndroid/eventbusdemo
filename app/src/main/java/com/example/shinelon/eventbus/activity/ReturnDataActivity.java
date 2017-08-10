package com.example.shinelon.eventbus.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shinelon.eventbus.R;
import com.example.shinelon.eventbus.eventbus.HeroEventBus;
import com.example.shinelon.eventbus.utils.ArrayDatas;
import com.example.shinelon.eventbus.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by TaeYang on 2017/8/7.
 * 利用EventBus发送数据返回上个界面
 */
public class ReturnDataActivity extends AppCompatActivity  {
    public static String NAME = "name";
    public static String AGE = "age";
    public static String CARD = "card";
    //图片背景
    private ImageView iv_img;
    //姓名、年龄，身份
    private EditText  et_name, et_age, et_card;
    /**
     * 上下文环境
     */
    private Context mContext;
    /**
     * 定义姓名、年龄、英雄类型（用来赋值传递过来的数据）
     */
    private String name, age, card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_data);
        mContext = this;
        name = getIntent().getStringExtra(NAME);
        age = getIntent().getStringExtra(AGE);
        card = getIntent().getStringExtra(CARD);
        //初始化UI
        initUI();
    }

    /**
     * 初始化UI控件
     */
    private void initUI() {
        //图片背景
        iv_img = (ImageView) findViewById(R.id.iv_img);
        //类型
        et_card = (EditText) findViewById(R.id.et_card);
        //姓名
        et_name = (EditText) findViewById(R.id.et_name);
        //年龄
        et_age = (EditText) findViewById(R.id.et_age);
        //设置图片
        iv_img.setImageResource(R.mipmap.ic_dj);
        //设置透明度
        iv_img.setAlpha(60);
       
        //赋值
        initData();
     
    }

   

    /**
     * 赋值英雄对象
     */
    private void initData() {
        et_name.setText(name);
        et_age.setText(age);
        et_card.setText(card);
        et_name.setSelection(name.length());
        et_age.setSelection(age.length());
        et_card.setSelection(card.length());
    }

    /**
     * 更改后的按钮
     *
     * @param view
     */
    public void ModifyHeroSuccess(View view) {
        ToastUtils.toast(mContext, "更改成功");
        name = et_name.getText().toString().trim();
        age = et_age.getText().toString().trim();
        card = et_card.getText().toString().trim();
        //发送要传递的数据
        EventBus.getDefault().post(new HeroEventBus(name, age, card));
        finish();
    }



}
