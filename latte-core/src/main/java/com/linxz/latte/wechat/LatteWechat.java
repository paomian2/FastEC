package com.linxz.latte.wechat;

import android.app.Activity;

import com.linxz.latte.app.ConfigKeys;
import com.linxz.latte.app.Latte;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月27日1:27  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LatteWechat {

    static final String APP_ID= Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID.name());
    static final String APP_SECRET=Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET.name());

    private final IWXAPI WXAPI;

    private LatteWechat(){
        final Activity activity=Latte.getConfiguration(ConfigKeys.ACTIVITY.name());
        WXAPI= WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    private static final class Holder{
        private static final LatteWechat INSTANCE=new LatteWechat();
    }

    public static LatteWechat getInstance(){
        return Holder.INSTANCE;
    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

}
