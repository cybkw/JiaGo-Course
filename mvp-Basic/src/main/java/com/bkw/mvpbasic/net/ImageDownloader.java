package com.bkw.mvpbasic.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bkw.mvpbasic.bean.ImageBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader implements Runnable {

    private final ImageBean imageBean;

    public ImageDownloader(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(imageBean.getUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("GET");
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageBean.setBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
