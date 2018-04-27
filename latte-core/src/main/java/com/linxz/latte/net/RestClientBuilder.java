package com.linxz.latte.net;

import android.content.Context;

import com.linxz.latte.net.callback.IError;
import com.linxz.latte.net.callback.IFailure;
import com.linxz.latte.net.callback.IRequest;
import com.linxz.latte.net.callback.ISuccess;
import com.linxz.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月06日17:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMAS=new WeakHashMap<>();
    private IRequest mIRequsst;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    private LoaderStyle mLoadStyle;
    private Context mContext;
    private File mFile;


    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }

    public final RestClientBuilder params(Map<String,Object> params){
        PARAMAS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
        PARAMAS.put(key,value);
        return this;
    }

    public final RestClientBuilder row(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=utf8"),raw);
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest){
        this.mIRequsst=iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError=iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext=context;
        this.mLoadStyle=style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoadStyle=LoaderStyle.BallClipRotateMultipleIndicator;
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }

    public final RestClientBuilder file(String path){
        this.mFile=new File(path);
        return this;
    }

    private Map<String,Object> checkParams(){
       return PARAMAS;
    }

    public final RestClient build(){
        return new RestClient(
                mUrl,
                PARAMAS,
                mIRequsst,
                mISuccess,
                mIFailure,
                mIError,
                mLoadStyle,
                mContext,
                mBody,
                mFile);
    }

}
