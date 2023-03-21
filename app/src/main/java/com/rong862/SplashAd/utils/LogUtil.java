package com.rong862.SplashAd.utils;

import com.rong862.SplashAd.BuildConfig;

import de.robv.android.xposed.XposedBridge;

public class LogUtil {

    private static final String TAG = "【开屏小能手】";

    public static void debug(String tag, String text){

        if(!BuildConfig.DEBUG)return;

        XposedBridge.log(TAG + tag + text);
    }

    public static void log(String tag, String text){

        XposedBridge.log(TAG + tag + text);
    }

}
