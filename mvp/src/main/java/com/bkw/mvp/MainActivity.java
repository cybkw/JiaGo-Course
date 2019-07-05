package com.bkw.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bkw.mvp.bean.ImageBean;
import com.bkw.mvp.contract.ImageDownContract;
import com.bkw.mvp.presenter.ImageDownPresenter;
import com.bkw.mvp.util.Constants;

public class MainActivity extends AppCompatActivity implements ImageDownContract.PV {

    private ImageView imageView;

    private ImageDownPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        presenter = new ImageDownPresenter(this);
    }

    public void getImage(View view) {
        ImageBean bean = new ImageBean();
        bean.setUrl(Constants.PATH);
        requestDownload(bean);
    }

    @Override
    public void requestDownload(ImageBean bean) {
        if (presenter != null) {
            presenter.requestDownload(bean);
        }
    }

    @Override
    public void responseDownloadResult(boolean isSucess, ImageBean bean) {
        if (isSucess) {
            imageView.setImageBitmap(bean.getBitmap());
        } else {
            Toast.makeText(this, "加载失败..", Toast.LENGTH_SHORT).show();
        }
    }
}
