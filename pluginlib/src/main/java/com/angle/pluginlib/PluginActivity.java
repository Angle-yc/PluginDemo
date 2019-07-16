package com.angle.pluginlib;

import android.app.Activity;
import android.os.Bundle;

/**
 * 作者    angle
 * 时间    2019/7/16 16:20
 * 文件    PluginApkDemo
 * 描述
 */
public class PluginActivity extends Activity implements IPlugin{
    private int mFrom = FROM_INTERNAL;
    public Activity mProxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
        mProxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if (saveInstanceState != null){
            mFrom = saveInstanceState.getInt("FROM");
        }
        if (mFrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
            mProxyActivity = this;
        }
    }

    @Override
    public void setContentView(int layoutRes) {
        if (mFrom == FROM_INTERNAL){
            super.setContentView(layoutRes);
        }else {
            mProxyActivity.setContentView(layoutRes);
        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAL){
            super.onResume();
        }
    }
}
