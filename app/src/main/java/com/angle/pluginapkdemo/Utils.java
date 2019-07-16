package com.angle.pluginapkdemo;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 作者    angle
 * 时间    2019/7/16 16:38
 * 文件    PluginApkDemo
 * 描述
 */
public class Utils {

    /***
     *  将Assets目录下的fileName文件拷贝至app缓存目录
     * @param context
     * @param fileName
     * @return
     */
    public static String copyAssetAndWrite(Context context, String fileName) {
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir, fileName);
            if (outFile.exists()){
                outFile.delete();
            }
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (res){
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            }else {
                Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
