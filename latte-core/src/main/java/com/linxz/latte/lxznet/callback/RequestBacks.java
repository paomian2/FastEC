package com.linxz.latte.lxznet.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月08日2:56  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class RequestBacks implements Callback<String>{

    private ISuccess success;
    private IError error;
    private IFailure failure;
    private IRequest request;

    public RequestBacks(ISuccess success, IError error, IFailure failure, IRequest request) {
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
         if (response.isSuccessful()){
             if (call.isExecuted()){
                 if (success!=null){
                     success.onSuccess(response.body());
                 }
             }
         }else{
             if (error!=null){
                 error.onError(response.code(),response.message());
             }
         }
         if (request!=null){
             request.onRequestEnd();
         }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (failure!=null){
            failure.onFailure();
        }
        if (request!=null){
            request.onRequestEnd();
        }
    }
}
