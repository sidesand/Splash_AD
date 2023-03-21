package com.rong862.SplashAd.plugin;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class MiuiMarketHook extends BaseHook{

    private static final String TAG = "【小米市场】";

    public MiuiMarketHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"小米市场启动...");

        Class<?> MarketTabClass = XposedHelpers.findClassIfExists("com.xiaomi.market.ui.splash.SplashManager", cl);

        if(MarketTabClass == null){
            log(TAG,"MarketTabClass is not exit !");
            return;
        }

        XposedHelpers.findAndHookMethod(MarketTabClass, "tryAdSplash", android.app.Activity.class, String.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                debug(TAG,"tryAdSplash set null !");
                return null;
            }
        });
    }
}
