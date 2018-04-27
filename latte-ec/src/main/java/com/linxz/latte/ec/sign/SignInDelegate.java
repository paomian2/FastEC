package com.linxz.latte.ec.sign;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import com.linxz.latte.delegates.LatteDelegate;
import com.linxz.latte.ec.R;
import com.linxz.latte.ec.R2;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月25日10:42  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class SignInDelegate extends LatteDelegate{

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignUp(){
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickSignInWechat(){

    }

    private boolean checkForm() {
        final String email = editSignInEmail.getText().toString().trim();
        final String password = editSignInPassword.getText().toString().trim();

        boolean isPass=true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("邮箱格式有误！");
            isPass=false;
        } else {
            editSignInEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请填写至少6位数密码！");
            isPass=false;
        } else {
            editSignInPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }
}
