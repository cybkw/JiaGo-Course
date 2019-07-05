package com.bkw.mvp.contract;

import com.bkw.mvp.bean.ImageBean;

/**
 * View层交互,Model交互共同的需求
 */
public interface ImageDownContract {

    interface M {
        //P层告知M层需要做的事情
        void requestDownload(ImageBean bean);
    }

    interface PV {
        //V层需要P层做的请求
        void requestDownload(ImageBean bean);

        //P层得到M层的处理结果,再通知V层
        void responseDownloadResult(boolean isSucess, ImageBean bean);
    }
}
