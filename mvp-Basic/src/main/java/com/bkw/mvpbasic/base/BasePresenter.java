package com.bkw.mvpbasic.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel, CONTRACT> {

    //M层
    protected M m;
    //绑定View层弱引用
    private WeakReference<V> vWeakReference;

    /**
     * RxJava订阅管理
     */
    protected RxManager mRxManager = new RxManager();

    public BasePresenter() {
        m = getModel();
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    public void unbindView() {
        mRxManager.unSubscribe();
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    //获取子类具体契约(Model层和View层的共同业务)
    public abstract CONTRACT getContract();

    public abstract M getModel();
}
