package com.rong862.SplashAd.plugin;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class LeKeHook extends BaseHook {

    private static final String TAG = "【乐刻运动】";

    public LeKeHook() {}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"乐刻运动启动...");

        XposedHelpers.findAndHookMethod("com.leoao.fitness.view.AdverisementView", cl, "checkAdvertisement", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                debug(TAG,"checkAdvertisement is call !...");
                param.setResult(null);
            }
        });

        XposedHelpers.findAndHookMethod("com.leoao.fitness.main.MainActivity", cl, "handleMainDialogs", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                debug(TAG,"handleMainDialogs is call !...");
                param.setResult(null);
            }
        });
    }
}
