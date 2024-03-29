package com.rong862.SplashAd.plugin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class BaiduMapHook extends BaseHook{

    private static final String TAG = "【百度地图】";

    public BaiduMapHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"百度地图启动...");

        XposedHelpers.findAndHookMethod(Intent.class, "getBooleanExtra", String.class, boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

                if(TextUtils.equals((String)param.args[0], "start_up_splash_flag")){
                    param.setResult(false);
                }
            }
        });
    }
}
