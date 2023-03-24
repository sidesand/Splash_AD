package com.rong862.SplashAd.utils;

import com.rong862.SplashAd.plugin.AiqiyiHook;
import com.rong862.SplashAd.plugin.BaiduMapHook;
import com.rong862.SplashAd.plugin.BaseHook;
import com.rong862.SplashAd.plugin.BilibiliHook;
import com.rong862.SplashAd.plugin.KugouHook;
import com.rong862.SplashAd.plugin.LeKeHook;
import com.rong862.SplashAd.plugin.MaimaiHook;
import com.rong862.SplashAd.plugin.MeituanHook;
import com.rong862.SplashAd.plugin.MiPayHook;
import com.rong862.SplashAd.plugin.MiuiMarketHook;
import com.rong862.SplashAd.plugin.MiuiMusicHook;
import com.rong862.SplashAd.plugin.NeteaseHook;
import com.rong862.SplashAd.plugin.SinaWeiboHook;
import com.rong862.SplashAd.plugin.TicketHook;
import com.rong862.SplashAd.plugin.ZhihuHook;
import com.rong862.SplashAd.plugin.ZhixingHook;

import java.util.HashMap;
import java.util.Map;

public class PackageUtil {

    public final static Map<String, Class<? extends BaseHook>> PACKAGE_MAP = new HashMap<>();

    static {
        //爱奇艺
        PACKAGE_MAP.put("com.qiyi.video", AiqiyiHook.class);
        //网易新闻
        PACKAGE_MAP.put("com.netease.newsreader.activity", NeteaseHook.class);
        //百度地图
        PACKAGE_MAP.put("com.baidu.BaiduMap", BaiduMapHook.class);
        //新浪微博
        PACKAGE_MAP.put("com.sina.weibo", SinaWeiboHook.class);
        //脉脉
        PACKAGE_MAP.put("com.taou.maimai", MaimaiHook.class);
        //小米应用市场
        PACKAGE_MAP.put("com.xiaomi.market", MiuiMarketHook.class);
        //小米音乐
        PACKAGE_MAP.put("com.miui.player", MiuiMusicHook.class);
        //小米钱包
        PACKAGE_MAP.put("com.mipay.wallet", MiPayHook.class);
        //知乎
        PACKAGE_MAP.put("com.zhihu.android", ZhihuHook.class);
        //酷狗
        PACKAGE_MAP.put("com.kugou.android", KugouHook.class);
        //智行旅行
        PACKAGE_MAP.put("cn.suanya.zhixing", ZhixingHook.class);
        //美团
        PACKAGE_MAP.put("com.sankuai.meituan", MeituanHook.class);
        //12306
        PACKAGE_MAP.put("com.MobileTicket", TicketHook.class);
        //乐刻运动
        PACKAGE_MAP.put("com.leoao.fitness", LeKeHook.class);
        //哔哩哔哩
        PACKAGE_MAP.put("tv.danmaku.bili", BilibiliHook.class);
    }
}
