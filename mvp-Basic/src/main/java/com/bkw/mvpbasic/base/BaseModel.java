package com.bkw.mvpbasic.base;

/**
 * 接收P层交给的业务(基类)
 */
public abstract class BaseModel<P extends BasePresenter, CONTRACT> {

    protected P p;

    /**
     * 业务处理完毕,通知P接收结果
     * 所以需要P的引用
     */
    public BaseModel(P p) {
        this.p = p;
    }

    public abstract CONTRACT getContract();

}
