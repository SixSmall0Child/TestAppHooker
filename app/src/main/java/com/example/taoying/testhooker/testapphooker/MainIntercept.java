package com.example.taoying.testhooker.testapphooker;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainIntercept implements IXposedHookLoadPackage {
    // zpp到此一游
    // 第二天zpp又来了
    private static final String TAG = "MainIntercept";
//    String className1 = "com.example.taoying.testbehooked.testappbehooked.MainActivity";
//    String className2 = "com.ss.android.article.base.feature.feed.docker.impl.misc.FeedInitializer_admiddle";
    String className3 = "com.ss.android.article.base.feature.feed.docker.impl.misc.FeedInitializer";
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("handleLoadPackage: "+ lpparam.processName + lpparam.packageName);
        Log.d(TAG, "handleLoadPackage: "+ lpparam.processName + lpparam.packageName);
        if (!lpparam.packageName.equals("com.ss.android.article.news")) return;
        /*XposedHelpers.findAndHookMethod(className2, lpparam.classLoader, "getDockers",List.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object[] args = param.args;
                List list = (List) args[0];
                list.clear();
                Log.d(TAG, "afterHookedMethod: ",new RuntimeException("aa"));
            }
        });*/

        XposedHelpers.findAndHookMethod(className3, lpparam.classLoader, "init", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Class<?> aClass = lpparam.classLoader.loadClass(className3);
                Field modules = XposedHelpers.findField(aClass, "MODULES");
                modules.set(null,new String[]{"feed","ugcdockers"});

//                Field rnPluginEventListener = XposedHelpers.findField(aClass, "rnPluginEventListener");
//                rnPluginEventListener.set(null,null);
//                modules.set(null,new String[]{"ugcdockers"});
            }
        });

        //todo
        /*if (!lpparam.packageName.equals("com.example.taoying.testbehooked.testappbehooked")) return;
        XposedHelpers.findAndHookMethod(className1, lpparam.classLoader, "getAd",
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return "被拦截啦。。。";
                    }
                });*/
    }
}
