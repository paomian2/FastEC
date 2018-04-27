package com.linxz.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import com.linxz.latte.delegates.LatteDelegate;
import com.linxz.latte.net.RestClient;
import com.linxz.latte.net.callback.IError;
import com.linxz.latte.net.callback.IFailure;
import com.linxz.latte.net.callback.ISuccess;
import com.linxz.latte.ui.LatteLoader;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月05日16:08  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testNet();
    }


    private void oldTestNet() {
        RestClient.builder()
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .oldRequest("https://www.baidu.com");
    }

    private void testNet() {
        RestClient.builder()
                .loader(getActivity())
                .url("http://127.0.0.1/index")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }


}
