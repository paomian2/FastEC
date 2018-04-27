package com.linxz.latte.net;
import com.linxz.latte.app.ConfigKeys;
import com.linxz.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月05日17:28  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class RestCreator {

   /**构建Retorift客户端*/
   private static final class RetrofitHolder{
       private static final String BASE_URL=Latte.getConfiguration(ConfigKeys.API_HOST.name());
       private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .client(OkHttpHolder.OK_HTTP_CLIENT)
               .addConverterFactory(ScalarsConverterFactory.create())
               .build();
   }

   /**构建OkHttp*/
   private static final class OkHttpHolder{
       private static final int TIME_OUT=60;
       private static final OkHttpClient.Builder BUILDER=new  OkHttpClient.Builder();
       private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR.name());
       private static OkHttpClient.Builder addInterceptor() {
           if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
               for (Interceptor interceptor : INTERCEPTORS) {
                   BUILDER.addInterceptor(interceptor);
               }
           }
           return BUILDER;
       }
       private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
               .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
               .build();
   }

   /**构建RestService*/
   private static final class RestServiceHolder{
       private static final RestService REST_SERVICE=RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
   }

    /**
     * @return RestService
     */
   public static RestService getRestService(){
       return RestServiceHolder.REST_SERVICE;
   }

}
