package com.bkw.mvpbasic.login;

import com.bkw.mvpbasic.bean.BaseBean;

/**
 * 将Model层,Presenter层,View层共同业务,封装成接口
 */
public interface LoginContract {
    interface Model {
        /***
         * 2-P交给M去处理业务
         * 处理请求
         * */
        void executeRequest(String uname, String pword);

    }

    /**
     * 结果实体必须是继承BaseBean的
     */
    interface View<T extends BaseBean> {
        /**
         * 返回结果,以泛型去接收(在项目开发中,结果一般都为实体Bean对象)
         * 4-接收P处理的结果
         */
        void handlerResult(T t);
    }

    interface Presenter<T extends BaseBean> {
        /**
         * 登录请求,验证参数
         * 1-View交给P层去做的业务
         */
        void request(String name, String pword);

        /**
         * 接收Model结果,通知View层更新
         * 3-M处理完了业务,P把处理结果告知V层
         */
        void response(T t);
    }


}
