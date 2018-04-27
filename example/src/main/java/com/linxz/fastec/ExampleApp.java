package com.linxz.fastec;
import android.app.Application;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.linxz.latte.app.Latte;
import com.linxz.latte.ec.database.DatabaseManager;
import com.linxz.latte.ec.icons.FontEcModule;
import com.linxz.latte.net.interceptors.DebugInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月04日16:17  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://192.168.1.11:9999/linxz-user/")
                .withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withWechatAppID("")
                .withWechatAppSecret("")
                .configure();
        DatabaseManager.getInstance().init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }


}
