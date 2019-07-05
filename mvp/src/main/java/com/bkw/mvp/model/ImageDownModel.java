package com.bkw.mvp.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bkw.mvp.bean.ImageBean;
import com.bkw.mvp.contract.ImageDownContract;
import com.bkw.mvp.presenter.ImageDownPresenter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownModel implements ImageDownContract.M {
    private ImageDownPresenter presenter;

    public ImageDownModel(ImageDownPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestDownload(final ImageBean bean) {
        new Thread(new Download(bean)).start();
    }

    final class Download implements Runnable {
        private final ImageBean bean;

        Download(ImageBean bean) {
            this.bean = bean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(bean.getUrl());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bean.setBitmap(bitmap);
                    presenter.responseDownloadResult(true, bean);
                } else {
                    presenter.responseDownloadResult(false, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
