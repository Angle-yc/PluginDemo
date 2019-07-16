package com.angle.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 作者    angle
 * 时间    2019/7/16 16:03
 * 文件    PluginApkDemo
 * 描述
 */
public class PluginManager {
    public static  final PluginManager instances = new PluginManager();
    private PluginManager(){}

    public static PluginManager getInstances(){
        return instances;
    }

    private Context mContext;
    private PluginApk mPluginApk;


    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    public PluginApk getPluginApk(){
        return mPluginApk;
    }

    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);

        if (packageInfo == null){
            return;
        }
        DexClassLoader classLoader = createClassLoader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources resources = createResource(am);
        mPluginApk = new PluginApk(classLoader,resources,packageInfo);
    }

    private Resources createResource(AssetManager am) {
        Resources resources = mContext.getResources();
        return new Resources(am,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**获取ClassLoader*/
    private DexClassLoader createClassLoader(String apkPath) {
        File file = mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }
}
