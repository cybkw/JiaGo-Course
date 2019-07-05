package com.bkw.mvp.presenter;

import com.bkw.mvp.model.ImageDownModel;
import com.bkw.mvp.MainActivity;
import com.bkw.mvp.bean.ImageBean;
import com.bkw.mvp.contract.ImageDownContract;

public class ImageDownPresenter implements ImageDownContract.PV {

    /**
     * 需要View层的引用
     */
    private MainActivity view;
    /**
     * 需要model去处理
     */
    private ImageDownModel model;

    public ImageDownPresenter(MainActivity view) {
        this.view = view;
        model = new ImageDownModel(this);
    }

    @Override
    public void requestDownload(ImageBean bean) {
        //接收到View层的指令,处理这个事情(可交给M层处理,也可自己处理)
        model.requestDownload(bean);
    }

    @Override
    public void responseDownloadResult(final boolean isSucess, final ImageBean bean) {
        //将处理的结果告知View层。
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.responseDownloadResult(isSucess, bean);
            }
        });

    }
}
