package com.bkw.mvpbasic.login;

import android.util.Log;

import com.bkw.mvpbasic.base.BaseModel;
import com.bkw.mvpbasic.bean.UserBean;

public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {


    private static final String TAG = "LoginModel>>>>>";

    /**
     * 业务处理结束,通知P接收结果
     * 所以需要P的引用
     *
     * @param loginPresenter
     */
    LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeRequest(String uname, String pword) {
                Log.e(TAG, "executeRequest:" + uname + "," + pword);
                if (uname.equals("bkw")) {
                    p.getContract().response(new UserBean(uname, pword));
                } else {
                    p.getContract().response(null);
                }

            }
        };
    }

}
