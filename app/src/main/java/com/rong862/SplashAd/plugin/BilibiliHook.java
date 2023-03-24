package com.rong862.SplashAd.plugin;

import android.content.Intent;
import android.text.TextUtils;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.log;

public class BilibiliHook extends BaseHook{

    private static final String TAG = "【哔哩哔哩】";

    public BilibiliHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"哔哩哔哩启动...");

        XposedHelpers.findAndHookMethod(Intent.class, "getAction", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                if(TextUtils.equals((String)param.getResult(), "android.intent.action.MAIN")){
                    param.setResult("");
                }
            }
        });
    }
}
