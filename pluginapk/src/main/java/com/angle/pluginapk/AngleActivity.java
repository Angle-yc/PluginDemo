package com.angle.pluginapk;

import android.os.Bundle;
import android.widget.Toast;

import com.angle.pluginlib.PluginActivity;

public class AngleActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle);
        Toast.makeText(mProxyActivity, "我是插件Activity", Toast.LENGTH_SHORT).show();
    }
}

