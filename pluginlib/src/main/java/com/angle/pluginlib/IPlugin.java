package com.angle.pluginlib;

import android.app.Activity;
import android.os.Bundle;

/**
 * 作者    angle
 * 时间    2019/7/16 16:17
 * 文件    PluginApkDemo
 * 描述
 */
public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onResume();

}
