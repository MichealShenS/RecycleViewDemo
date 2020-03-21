package com.ssq.recyclerviewdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Author : Mr.Shen
 * Date : 2020/3/21 8:21
 * Description :
 */
public class ToastUtil {

    private static Toast sToast = null;

    /**
     * 只显示一次短的Toast
     */
    public static void showSingleShort(Context context, CharSequence text) {
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(text);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();
    }

    /**
     * 只显示一次长的Toast
     */
    public static void showSingleLong(Context context, CharSequence text) {
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            sToast.setText(text);
            sToast.setDuration(Toast.LENGTH_LONG);
        }
        sToast.show();
    }

    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (isShow) {
            Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT).show();
            //解决小米手机带应用名的问题
//            if (sToast == null) {
//                sToast = Toast.makeText(MyApplication.getContext(), null, Toast.LENGTH_SHORT);
//                sToast.setText(message);
//            } else {
//                sToast.setText(message);
//            }
//            sToast.show();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (isShow)
            Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

}