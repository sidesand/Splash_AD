package com.rong862.SplashAd.plugin;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class MiPayHook extends BaseHook{

    private static final String TAG = "【小米钱包】";

    public MiPayHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"小米钱包启动...");

        Class<?> MiFinanceClass = XposedHelpers.findClassIfExists("com.xiaomi.jr.app.MiFinanceActivity", cl);

        if (MiFinanceClass == null) {
            log(TAG,"MiFinanceClass is not exit !");
            return;
        }

        XposedHelpers.findAndHookMethod(MiFinanceClass, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                for(Field field : MiFinanceClass.getDeclaredFields()){
                    if(field.getType().getName().equals("com.xiaomi.jr.app.splash.SplashFragment")){
                        field.setAccessible(true);
                        Object splashFragment = field.get(param.thisObject);
                        debug(TAG,"SplashFragment call method close...");
                        XposedHelpers.callMethod(splashFragment, "close");
                        break;
                    }
                }
            }
        });
    }
}
