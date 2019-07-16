package com.angle.pluginapkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.angle.pluginlib.PluginManager;
import com.angle.pluginlib.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstances().init(this);
    }

    public void load(View view) {
        String apkPath = Utils.copyAssetAndWrite(MainActivity.this,"aaa.apk");
        PluginManager.getInstances().loadApk(apkPath);
    }

    public void jump(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ProxyActivity.class);
        intent.putExtra("className","com.angle.pluginapk.AngleActivity");
        startActivity(intent);
    }


}
