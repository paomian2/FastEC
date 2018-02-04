package com.linxz.latte.app;
import java.util.HashMap;
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
    }

    public Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(),host);
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
