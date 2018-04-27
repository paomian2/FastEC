package com.linxz.latte.lxznet;

import com.linxz.latte.lxznet.callback.IError;
import com.linxz.latte.lxznet.callback.IFailure;
import com.linxz.latte.lxznet.callback.IRequest;
import com.linxz.latte.lxznet.callback.ISuccess;
import com.linxz.latte.lxznet.callback.RequestBacks;
import com.linxz.latte.net.HttpMethod;
import com.linxz.latte.net.RestService;

import java.util.WeakHashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月08日2:48  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LxzRestClient {

    private String url;
    private WeakHashMap<String, Object> params;
    private ISuccess success;
    private IError error;
    private IFailure failure;
    private IRequest request;

    public LxzRestClient(String url, WeakHashMap<String, Object> params, ISuccess success, IError error, IFailure failure, IRequest request) {
        this.url = url;
        this.params = params;
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WeakHashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(WeakHashMap<String, Object> params) {
        this.params = params;
    }

    public ISuccess getSuccess() {
        return success;
    }

    public void setSuccess(ISuccess success) {
        this.success = success;
    }

    public IError getError() {
        return error;
    }

    public void setError(IError error) {
        this.error = error;
    }

    public IFailure getFailure() {
        return failure;
    }

    public void setFailure(IFailure failure) {
        this.failure = failure;
    }

    public IRequest getRequest() {
        return request;
    }

    public void setRequest(IRequest request) {
        this.request = request;
    }

    public void oldRequest(String url, WeakHashMap<String, Object> params, Callback callback) {
        Call<String> call;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1/")
                .client(new OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        RestService service = retrofit.create(RestService.class);
        call = service.get(url, params);
        call.enqueue(callback);
    }

    private void request(HttpMethod method){
        Call<String> call=null;
        LinxzApi api= LinxzRestCreator.getApiService();
        switch (method){
            case GET:
                call=api.get(url,params);
                break;
            case POST:
                call=api.post(url,params);
                break;
            case PUT:
                call=api.put(url,params);
                break;
            case DELETE:
                call=api.delete(url,params);
                break;
            default:
                break;
        }
        call.enqueue(new RequestBacks(success,error,failure,request));
    }

    public void get(){
        request(HttpMethod.GET);
    }

}
