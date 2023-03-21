package com.rong862.SplashAd.plugin;


import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class MeituanHook extends BaseHook{

    private static final String TAG = "【美团】";

    public MeituanHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"美团启动...");

        Class<?> MaterialMapClass = XposedHelpers.findClassIfExists("com.meituan.android.pt.homepage.startup.StartupPicture$MaterialMap", cl);

        if(MaterialMapClass != null){

            XposedHelpers.findAndHookMethod(MaterialMapClass, "hasImageList", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    debug(TAG, "hasImageList-->" + param.getResult());
                    param.setResult(false);
                }
            });

            XposedHelpers.findAndHookMethod(MaterialMapClass, "hasVideoList", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    debug(TAG, "hasVideoList-->" + param.getResult());
                    param.setResult(false);
                }
            });

            XposedHelpers.findAndHookMethod(MaterialMapClass, "isAdPlatform", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    debug(TAG, "isAdPlatform-->" + param.getResult());
                    param.setResult(false);
                }
            });

        }else {log(TAG, "MaterialMap class is null!");}
    }
}