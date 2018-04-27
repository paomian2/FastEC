package com.linxz.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.linxz.latte.delegates.LatteDelegate;
import com.linxz.latte.ec.R;
import com.linxz.latte.ui.launcher.LaunchHolderCreator;
import com.linxz.latte.ui.launcher.ScrollLauncherTag;
import com.linxz.latte.utils.storage.LattePreference;

import java.util.ArrayList;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月25日0:01  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LaunchScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner=null;
    private final ArrayList<Integer> INGERGERS=new ArrayList<>();
    private void initBanner(){
        INGERGERS.add(R.mipmap.launcher_00);
        INGERGERS.add(R.mipmap.launcher_01);
        INGERGERS.add(R.mipmap.launcher_02);
        INGERGERS.add(R.mipmap.launcher_03);
        INGERGERS.add(R.mipmap.launcher_04);
        INGERGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LaunchHolderCreator(),INGERGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }
    @Override
    public Object setLayout() {
        mConvenientBanner=new ConvenientBanner<>(_mActivity);
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position==INGERGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
        }
    }
}
