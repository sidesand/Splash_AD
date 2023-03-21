package com.rong862.SplashAd.plugin;

import android.app.Activity;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class MiuiMusicHook extends BaseHook{

    private static final String TAG = "【小米音乐】";

    public MiuiMusicHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"小米音乐启动...");

        Class<?> SplashAdClass = XposedHelpers.findClassIfExists("com.tencent.qqmusiclite.activity.SplashAdActivity", cl);

        if(SplashAdClass == null){
            log(TAG,"class SplashAdClass is not exit !");
            return;
        }

        XposedHelpers.findAndHookMethod(SplashAdClass, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                debug(TAG,"SplashAdActivity finish !");
                ((Activity)param.thisObject).finish();
            }
        });
    }
}
