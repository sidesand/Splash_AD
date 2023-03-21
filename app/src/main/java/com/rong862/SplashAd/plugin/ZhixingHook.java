package com.rong862.SplashAd.plugin;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class ZhixingHook extends BaseHook{

    private static final String TAG = "【智行出行】";

    public ZhixingHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"智行出行启动...");

        Class<?> TripAdManagerClass = XposedHelpers.findClassIfExists("com.app.base.tripad.TripAdManager", cl);

        if(TripAdManagerClass != null){
            XposedHelpers.findAndHookMethod(TripAdManagerClass, "getCanShowSplashAdMark", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    debug(TAG,"getCanShowSplashAdMark getResult:" + param.getResult());
                    param.setResult(false);
                }
            });
        }else log(TAG,"Class is not exit...");
    }
}