package com.example.loveb.fds;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 何建钦 on 2017/12/13.
 */

public class ImageLoadAsyncTask extends AsyncTask<String,Void,Bitmap> {


    /**
     * 定义一个图片的接口回调
     */
    public interface ImageCallBack{
        void callBitmap(Bitmap bitmap);
    }

    //定义变量记住调用者所传递进来的回调监听对象
    private ImageCallBack imageCallBack;
    //构造方法
    public ImageLoadAsyncTask(ImageCallBack imageCallBack){

        this.imageCallBack = imageCallBack;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(bitmap != null){
            //设置图片 回调回去
            imageCallBack.callBitmap(bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            String path = params[0];
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*1000);
            connection.setReadTimeout(5*1000);
            //服务器响应
            int code = connection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){//判断服务器是否连接成功并相应
                //图片流
                InputStream is = connection.getInputStream();
                //将图片流转化成Bitmap位图，返回
                return BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
