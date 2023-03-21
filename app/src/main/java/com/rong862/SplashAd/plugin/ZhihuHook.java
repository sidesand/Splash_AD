package com.rong862.SplashAd.plugin;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.debug;
import static com.rong862.SplashAd.utils.LogUtil.log;

public class ZhihuHook extends BaseHook{

    private static final String TAG = "【知乎】";

    public ZhihuHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"知乎启动...");

        Class<?> AdClass = XposedHelpers.findClassIfExists("com.zhihu.android.app.util.cy", cl);

        if(AdClass != null){
            XposedHelpers.findAndHookMethod(AdClass, "isShowLaunchAd", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    debug(TAG,"isShowLaunchAd is --->" + param.getResult());
                    param.setResult(false);
                }
            });
        }else log(TAG,"Class is not exit...");
    }
}
