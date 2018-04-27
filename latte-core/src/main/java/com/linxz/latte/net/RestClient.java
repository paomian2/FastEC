package com.linxz.latte.net;

import android.content.Context;

import com.linxz.latte.net.callback.IError;
import com.linxz.latte.net.callback.IFailure;
import com.linxz.latte.net.callback.IRequest;
import com.linxz.latte.net.callback.ISuccess;
import com.linxz.latte.net.callback.RequstCallBacks;
import com.linxz.latte.ui.LatteLoader;
import com.linxz.latte.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月05日17:22  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class RestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMAS;
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final RequestBody REQUEST_BODY;
    private final File FILE;


    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest irequest,
                      ISuccess isuccess,
                      IFailure ifailure,
                      IError ierror,
                      LoaderStyle style,
                      Context context,
                      RequestBody requestBody,
                      File file) {
        URL = url;
        PARAMAS = params;
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
        LOADER_STYLE = style;
        CONTEXT = context;
        REQUEST_BODY = requestBody;
        FILE = file;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public void oldRequest(final String url) {
        Call<String> call;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:9999/linxz-user/")
                .client(new OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        RestService service = retrofit.create(RestService.class);
        call = service.get(url, PARAMAS);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (getRequestCallbacks() != null) {
                    getRequestCallbacks().onResponse(call, response);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    /**
     * 发起网络请求
     */
    private void requst(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMAS);
                break;
            case POST:
                call = service.post(URL, PARAMAS);
                break;
            case PUT:
                call = service.put(URL, PARAMAS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMAS);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body = MultipartBody.Part.create(requestBody);
                call = service.upload(URL, body);
                break;
            case POST_RAW:
                call = service.postRaw(URL, REQUEST_BODY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, REQUEST_BODY);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallbacks());
        }
    }

    private Callback<String> getRequestCallbacks() {
        return new RequstCallBacks(
                IREQUEST,
                ISUCCESS,
                IFAILURE,
                IERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        requst(HttpMethod.GET);
    }

    public final void post() {
        if (REQUEST_BODY != null) {
            requst(HttpMethod.POST_RAW);
        } else {
            requst(HttpMethod.POST);
        }
    }

    public final void put() {
        if (REQUEST_BODY != null) {
            requst(HttpMethod.PUT_RAW);
        } else {
            requst(HttpMethod.PUT);
        }
    }

    public final void delete() {
        requst(HttpMethod.DELETE);
    }

}
