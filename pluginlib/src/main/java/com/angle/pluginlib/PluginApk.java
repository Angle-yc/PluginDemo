package com.angle.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * 作者    angle
 * 时间    2019/7/16 16:00
 * 文件    PluginApkDemo
 * 描述
 */
public class PluginApk {

    public DexClassLoader mClassLoader;
    public Resources mResource;
    public PackageInfo mPackageInfo;
    public AssetManager mAssetManager;

    public PluginApk(DexClassLoader classLoader, Resources resource, PackageInfo packageInfo) {
        this.mClassLoader = classLoader;
        this.mResource = resource;
        this.mPackageInfo = packageInfo;
        this.mAssetManager = mResource.getAssets();
    }
}
