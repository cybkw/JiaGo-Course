package com.bkw.mvpbasic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bkw.mvpbasic.base.BaseView;
import com.bkw.mvpbasic.bean.UserBean;
import com.bkw.mvpbasic.login.LoginContract;
import com.bkw.mvpbasic.login.LoginPresenter;

public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {

    private static final String TAG = "MVP>>>>>>>>>";
    private EditText etUser;
    private EditText etPass;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_password);
        btLogin = findViewById(R.id.bt_login);
        initUi();
    }

    private void initUi() {

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = etUser.getText().toString();
                String pword = etPass.getText().toString();

                Log.e(TAG, "btLogin:" + uname + "," + pword);
                p.getContract().request(uname, pword);
            }
        });
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserBean>() {
            @Override
            public void handlerResult(UserBean userBean) {
                if (userBean == null) {
                    Toast.makeText(getApplication(), "登录失败!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), String.format("您的登录名为:%s", userBean.getUsername()), Toast.LENGTH_SHORT).show();
                }
            }

        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
