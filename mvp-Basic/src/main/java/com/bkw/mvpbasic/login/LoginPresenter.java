package com.bkw.mvpbasic.login;


import android.util.Log;

import com.bkw.mvpbasic.LoginActivity;
import com.bkw.mvpbasic.base.BasePresenter;
import com.bkw.mvpbasic.bean.UserBean;

public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel, LoginContract.Presenter> {
    private static final String TAG = "LoginPresenter>>>>>>";

    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserBean>() {
            @Override
            public void request(String name, String pword) {
                Log.e(TAG, "request");
                m.getContract().executeRequest(name, pword);
            }

            @Override
            public void response(UserBean userBean) {
                getView().getContract().handlerResult(userBean);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }


}
