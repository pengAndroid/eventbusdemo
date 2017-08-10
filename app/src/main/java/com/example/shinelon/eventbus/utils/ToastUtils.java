package com.example.shinelon.eventbus.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by TaeYang on 2017/8/7.
 */

public class ToastUtils {
    /**
     * 显示toast提示框，此提示框会显示在Activity的中部
     * Create by Peng on  2017/8/7.
     *
     * @param msg 提示的消息
     */
    public static void toast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
