package com.linxz.latte.app;
import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月04日16:24  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class Configurator {

    private static final HashMap<String,Object> LATTE_CONFIGS=new HashMap<>();
    private static final List<IconFontDescriptor> ICONS=new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        private final static Configurator INSTANCE=new Configurator();
    }

    public final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
        initIcons();
    }

    public Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(),host);
        return this;
    }

    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    public final Configurator withWechatAppID(String appID) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID.name(), appID);
        return this;
    }

    public final Configurator withWechatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET.name(), appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY.name(), activity);
        return this;
    }



    private void chcekConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("configuration is no ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        chcekConfiguration();
        final Object value=LATTE_CONFIGS.get(key);
        if (value==null){
            throw  new NullPointerException(key.toString()+" IS NULL");
        }
        return (T) value;
    }

}
