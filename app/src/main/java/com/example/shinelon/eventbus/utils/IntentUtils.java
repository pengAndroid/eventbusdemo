package com.example.shinelon.eventbus.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.example.shinelon.eventbus.activity.ReturnDataActivity;
import com.example.shinelon.eventbus.eventbus.HeroEventBus;

/**
 * 跳转界面的工具类
 * Created by TaeYang on 2017/8/7.
 */

public class IntentUtils {
    /**
     * @param context 上下文
     * @param name    姓名
     * @param age     年龄
     * @param card    英雄身份
     */
    public static void startReturnDataActivity(Context context, String name, String age, String card) {
        Intent intent = new Intent(context, ReturnDataActivity.class);
        intent.putExtra(ReturnDataActivity.NAME, name);
        intent.putExtra(ReturnDataActivity.AGE, age);
        intent.putExtra(ReturnDataActivity.CARD, card);
        context.startActivity(intent);
    }
}
