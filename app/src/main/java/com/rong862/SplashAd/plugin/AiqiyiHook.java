package com.rong862.SplashAd.plugin;

import android.content.Intent;
import android.text.TextUtils;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class AiqiyiHook extends BaseHook{

    private static final String TAG = "【爱奇艺】";

    public AiqiyiHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"爱奇艺启动...");

        XposedHelpers.findAndHookMethod(Intent.class, "getStringExtra", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

                if(TextUtils.equals("extra_origin_component", (String)param.args[0])){

                    debug(TAG,"getStringExtra is called !");

                    param.setResult("org.qiyi.android.video.MainActivity");
                }
            }
        });
    }
}
