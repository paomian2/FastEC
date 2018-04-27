package com.linxz.latte.net.callback;

import android.content.Context;
import android.os.Handler;

import com.linxz.latte.ui.LatteLoader;
import com.linxz.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月08日1:18  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class RequstCallBacks implements Callback<String>{

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER=new Handler();

    public RequstCallBacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        IREQUEST = request;
        ISUCCESS = success;
        IFAILURE = failure;
        IERROR = error;
        LOADER_STYLE=style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (ISUCCESS!=null){
                    ISUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if (IERROR!=null){
                IERROR.onError(response.code(),response.message());
            }
        }
        if (IREQUEST!=null){
            IREQUEST.onRequesstEnd();
        }
        if (LOADER_STYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE!=null){
            IFAILURE.onFailure();
        }
        if (IREQUEST!=null){
            IREQUEST.onRequesstEnd();
        }
        if (LOADER_STYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }

    }
}
