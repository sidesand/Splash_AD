package com.rong862.SplashAd;

import android.content.Context;
import android.content.ContextWrapper;

import com.rong862.SplashAd.plugin.BaseHook;
import com.rong862.SplashAd.utils.PackageUtil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class MainHook implements IXposedHookLoadPackage{

    private static final String TAG = "【LoadPackage】";

    private static boolean isLoadPlugin = false;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam){

        if(!PackageUtil.PACKAGE_MAP.containsKey(loadPackageParam.packageName))return;

        log(TAG, loadPackageParam.packageName + " is loading ! !");

        Class<? extends BaseHook> mClass = PackageUtil.PACKAGE_MAP.get(loadPackageParam.packageName);

        XposedHelpers.findAndHookMethod(ContextWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                if(isLoadPlugin){
                    debug(TAG, "plugin is already load !!!");
                    return;
                }

                isLoadPlugin = true;

                Context appContext = (Context) param.args[0];

                if(mClass != null)mClass.newInstance().startHook(appContext.getClassLoader());
            }
        });

    }
}
