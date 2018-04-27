package com.linxz.latte.ec.sign;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import com.linxz.latte.delegates.LatteDelegate;
import com.linxz.latte.ec.R;
import com.linxz.latte.ec.R2;
import com.linxz.latte.net.RestClient;
import com.linxz.latte.net.callback.ISuccess;
import com.linxz.latte.utils.log.LatteLogger;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月25日0:56  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password1)
    TextInputEditText editSignUpPassword1;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up_regiester)
    AppCompatButton btnSignUpRegiester;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;

    private ISignListener mISignListener=null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener= (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up_regiester)
    void onClickSignUp(){
        if (checkForm()){
            RestClient.builder()
                    .loader(getContext())
                    .url("http://lstreamlet.vicp.io/linxz-user/fastEc/user_profile")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            Logger.json(response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .get();

        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickSignIn(){
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = editSignUpName.getText().toString().trim();
        final String email = editSignUpEmail.getText().toString().trim();
        final String phone = editSignUpPhone.getText().toString().trim();
        final String password = editSignUpPassword1.getText().toString().trim();
        final String rePassword = editSignUpRePassword.getText().toString().trim();

        boolean isPass=true;
        if (name.isEmpty()) {
            editSignUpName.setError("请输入姓名！");
            isPass=false;
        } else {
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("邮箱格式有误！");
            isPass=false;
        } else {
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手机号码格式有误！");
            isPass=false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword1.setError("请填写至少6位数密码！");
            isPass=false;
        } else {
            editSignUpPassword1.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6) {
            editSignUpRePassword.setError("请填写至少6位数密码！");
            isPass=false;
        } else {
            editSignUpPassword1.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
