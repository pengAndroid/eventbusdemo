package com.example.shinelon.eventbus.activity;

import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinelon.eventbus.R;
import com.example.shinelon.eventbus.eventbus.HeroEventBus;
import com.example.shinelon.eventbus.utils.IntentUtils;
import com.example.shinelon.eventbus.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by TaeYang on 2017/8/7.
 * 收到数据后处理更新的界面
 */
public class ReceiveDataActivity extends AppCompatActivity {
    //图片背景
    private ImageView iv_img;
    //姓名、年龄，身份
    private TextView tv_name, tv_age, tv_card;
    /**
     * 上下文环境
     */
    private Context mContext;
    private String name;
    private String age;
    private String card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);
        mContext = this;
        //注册EventBus
        EventBus.getDefault().register(mContext);
        //初始化UI
        initUI();
    }

    /**
     * 返回后的数据处理更新状态
     *
     * @param heroEventBus
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHeroEvent(HeroEventBus heroEventBus) {
        //null在前减少空指针异常
        if (null != heroEventBus) {
            tv_name.setText(heroEventBus.getName());
            tv_age.setText(heroEventBus.getAge());
            tv_card.setText(heroEventBus.getCard());
            //保存最新信息
            saveNewInfo(heroEventBus.getCard(), heroEventBus.getName(), heroEventBus.getAge());
        }
    }


    /**
     * 初始化UI控件
     */
    private void initUI() {
        //图片背景
        iv_img = (ImageView) findViewById(R.id.iv_img);
        //姓名
        tv_name = (TextView) findViewById(R.id.tv_name);
        //年龄
        tv_age = (TextView) findViewById(R.id.tv_age);
        //身份
        tv_card = (TextView) findViewById(R.id.tv_card);
        //设置图片
        iv_img.setImageResource(R.mipmap.ic_libai);
        //设置透明度
        iv_img.setAlpha(60);

        //获取已有数据
        getNewInfo();
    }

    /**
     * 更换英雄按钮点击事件
     */
    public void goModifyHero(View view) {
        //获取姓名
        name = tv_name.getText().toString().trim();
        //获取年龄
        age = tv_age.getText().toString().trim();
        //获取英雄类型
        card = tv_card.getText().toString().trim();
        //启动另一个界面(上下文，传递的对象)
        IntentUtils.startReturnDataActivity(mContext, name, age, card);
    }

    /**
     * 保存最新信息
     *
     * @param card 英雄类型
     * @param name 英雄姓名
     * @param age  年龄
     */
    private void saveNewInfo(String card, String name, String age) {
        SharedPreferencesUtils.put(mContext, "new_card", card);
        SharedPreferencesUtils.put(mContext, "new_name", name);
        SharedPreferencesUtils.put(mContext, "new _age", age);
    }

    /**
     * 获取最新信息
     */
    private void getNewInfo() {
        if (null != SharedPreferencesUtils.get(mContext, "new_card", "")) {
            tv_card.setText((String) SharedPreferencesUtils.get(mContext, "new_card", ""));
        }
        if (null != SharedPreferencesUtils.get(mContext, "new_name", "")) {
            tv_name.setText((String) SharedPreferencesUtils.get(mContext, "new_name", ""));
        }
        if (null != SharedPreferencesUtils.get(mContext, "new _age", "")) {
            tv_age.setText((String) SharedPreferencesUtils.get(mContext, "new _age", ""));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑EventBus
        EventBus.getDefault().unregister(mContext);
    }
}
