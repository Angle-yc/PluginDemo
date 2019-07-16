package com.angle.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;


/**
 * 作者    angle
 * 时间    2019/7/16 16:26
 * 文件    PluginApkDemo
 * 描述
 */
public class ProxyActivity extends Activity{

    private String mClassName;
    private PluginApk mPluginApk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstances().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (mPluginApk == null){
            Log.e("====>","Loading apk file first please");
            return;
        }
        try {
            Class<?> clazz = mPluginApk.mClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin){
                IPlugin iPlugin = (IPlugin) object;
                iPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk != null ? mPluginApk.mAssetManager : super.getAssets();
    }

    @Override
    public Resources getResources() {
        return mPluginApk != null ? mPluginApk.mResource : super.getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.mClassLoader : super.getClassLoader();
    }
}
