package com.rong862.SplashAd.plugin;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.log;


public class XimalayaHook extends BaseHook{

    private static final String TAG = "【喜马拉雅】";

    public XimalayaHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"喜马拉雅启动...");

        XposedHelpers.findAndHookMethod("com.ximalaya.ting.android.adsdk.AdSDK", cl, "getProvider", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.setResult(null);
            }
        });
    }
}
