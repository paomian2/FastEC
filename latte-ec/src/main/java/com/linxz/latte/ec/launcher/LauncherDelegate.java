package com.linxz.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import com.linxz.latte.delegates.LatteDelegate;
import com.linxz.latte.ec.R;
import com.linxz.latte.ec.R2;
import com.linxz.latte.ui.launcher.ScrollLauncherTag;
import com.linxz.latte.utils.storage.LattePreference;
import com.linxz.latte.utils.timer.BaseTimerTask;
import com.linxz.latte.utils.timer.ITimerListener;
import java.text.MessageFormat;
import java.util.Timer;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月10日13:52  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){

    }


    private Timer mTimer = null;
    private int mCount = 5;

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    /**
     * 判断是否显示滑动启动页
     * */
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            getSupportDelegate().start(new LaunchScrollDelegate(),SINGLETASK);
        }else{
            //检测用户是否登录了APP
        }
    }

    @Override
    public void onTimer() {
        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }


}