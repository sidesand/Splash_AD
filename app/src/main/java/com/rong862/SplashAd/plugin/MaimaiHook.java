package com.rong862.SplashAd.plugin;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class MaimaiHook extends BaseHook{

    private static final String TAG = "【脉脉】";

    public MaimaiHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"脉脉启动...");

        Class<?> MainViewClass = XposedHelpers.findClassIfExists("com.taou.maimai.MainViewModel", cl);

        if(MainViewClass == null){
            log(TAG,"MainViewClass is not exit !");
            return;
        }

        XposedBridge.hookAllMethods(MainViewClass, "showColdStartAd", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam){

                debug(TAG,"showColdStartAd is Replacemented...");
                return null;
            }
        });
    }
}
