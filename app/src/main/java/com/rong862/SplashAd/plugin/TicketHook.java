package com.rong862.SplashAd.plugin;

import android.view.View;
import android.widget.ImageView;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import static com.rong862.SplashAd.utils.LogUtil.log;

public class TicketHook extends BaseHook{

    private static final String TAG = "【12306】";

    public TicketHook(){}

    @Override
    public void startHook(ClassLoader cl) {

        log(TAG,"12306启动...");

        Class<?> SplashAdDialogClass = XposedHelpers.findClassIfExists("com.MobileTicket.ui.dialog.SplashAdDialog", cl);

        XposedHelpers.findAndHookMethod("com.MobileTicket.ui.activity.MainActivity", cl, "findViews", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                ImageView img = (ImageView)XposedHelpers.getObjectField(param.thisObject, "mSplashPlaceHolder");
                img.setVisibility(View.GONE);
            }
        });

        if(SplashAdDialogClass != null){
            XposedHelpers.findAndHookMethod(SplashAdDialogClass, "show", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.setResult(null);
                }
            });
        }else log(TAG,"Class is not exit...");
    }
}
