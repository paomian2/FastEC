package com.linxz.latte.lxznet;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月08日3:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LinxzRestCreator {

    /**构建Retorfit*/
    public static final class RetorfitHolder{
        public static final Retrofit RETROFIT=new Retrofit.Builder()
                .baseUrl("http://127.0.0.1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .build();
    }

    /**构建OkHttpClien*/
    private static final class OkHttpClientHolder{
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

    }

    /**构建ApiService*/
    private static final class ApiServiceHolder{
        private static final LinxzApi LINXZ_API=RetorfitHolder.RETROFIT.create(LinxzApi.class);
    }

    /**获取ApiService*/
    public static LinxzApi getApiService(){
        return ApiServiceHolder.LINXZ_API;
    }

    /**构建请求参数*/
    private static final class ParamasHolder{
        private static final WeakHashMap<String,Object> WEAK_HASH_MAP=new WeakHashMap<>();
    }

    /**获取参数容器*/
    public static WeakHashMap<String,Object> getParams(){
        return ParamasHolder.WEAK_HASH_MAP;
    }


}
