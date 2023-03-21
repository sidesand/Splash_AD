package com.rong862.SplashAd.plugin;

import android.app.Activity;
import android.content.Intent;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class KugouHook extends BaseHook{

    private static final String TAG = "【酷狗】";

    public KugouHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"酷狗启动...");

        Class<?> SplashAdClass = XposedHelpers.findClassIfExists("com.kugou.android.app.splash.SplashActivity", cl);
        Class<?> mainClass = XposedHelpers.findClassIfExists("com.kugou.android.app.MediaActivity", cl);

        if(SplashAdClass == null || mainClass == null){
            log(TAG,"class is not exit !");
            return;
        }

        XposedHelpers.findAndHookMethod(SplashAdClass, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                Activity AdActivity = (Activity)param.thisObject;
                Intent intent = new Intent(AdActivity, mainClass);
                AdActivity.startActivity(intent);
                debug(TAG,"AdActivity finish...");
                AdActivity.finish();
            }
        });
    }
}
