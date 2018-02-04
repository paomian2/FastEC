package com.linxz.latte.app;

import android.content.Context;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月04日15:59  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public final class Latte {

   public static Configurator init(Context context){
       Configurator.getInstance()
               .getLatteConfigs()
               .put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
       return Configurator.getInstance();
   }

   public static Configurator getConfigurator(){
       return Configurator.getInstance();
   }

   public static <T> T getConfiguration(String key){
       return getConfigurator().getConfiguration(key);
   }

   public static Context getApplicationContext(){
       return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
   }

}
