package com.rong862.SplashAd.plugin;

import android.app.Activity;
import android.content.Intent;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class NeteaseHook extends BaseHook{

    private static final String TAG = "【网易新闻】";

    public NeteaseHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"网易新闻启动...");

        Class<?> newsClass = XposedHelpers.findClassIfExists("com.netease.nr.biz.ad.newAd.AdActivity",cl);
        Class<?> mainClass = XposedHelpers.findClassIfExists("com.netease.nr.phone.main.MainActivity",cl);

        if(newsClass == null || mainClass == null){
            log(TAG,"mainClass | newsClass is not exit !");
        }else{
            XposedHelpers.findAndHookMethod(newsClass, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Activity AdActivity = (Activity)param.thisObject;
                    Intent intent = new Intent(AdActivity, mainClass);
                    AdActivity.startActivity(intent);
                    debug(TAG,"NeteaseHook: AdActivity finish...");
                    AdActivity.finish();
                }
            });
        }

        Class<?> AdLoadClass = XposedHelpers.findClassIfExists("com.netease.newsreader.common.ad.e.a", cl);
        Class<?> AdInfoClass = XposedHelpers.findClassIfExists("com.netease.newad.adinfo.AdInfo", cl);

        if(AdLoadClass == null || AdInfoClass == null){
            log(TAG,"AdLoadClass is not exit !");
        }else{
            XposedHelpers.findAndHookMethod(AdLoadClass, "a", AdInfoClass, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    debug(TAG,"AdLoad set null...");
                    param.args[0] = null;
                }
            });
        }
    }
}
