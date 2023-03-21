package com.rong862.SplashAd.plugin;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class SinaWeiboHook extends BaseHook{

    private static final String TAG = "【新浪微博】";

    public SinaWeiboHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"新浪微博启动...");

        Class<?> SplashClass = XposedHelpers.findClassIfExists("com.sina.weibo.SplashActivity", cl);
        Class<?> mainClass = XposedHelpers.findClassIfExists("com.sina.weibo.MainTabActivity", cl);

        if(SplashClass != null && mainClass != null){
            //去除开屏广告
            XposedHelpers.findAndHookMethod(SplashClass, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Activity AdActivity = (Activity)param.thisObject;
                    Intent intent = new Intent(AdActivity, mainClass);
                    AdActivity.startActivity(intent);
                    debug(TAG,"SplashActivity finish...");
                    AdActivity.finish();
                }
            });

        }else{log(TAG,"SplashClass or mainClass is not exit...");}
    }
}
