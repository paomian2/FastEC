package com.linxz.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月25日0:18  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LaunchHolderCreator implements CBViewHolderCreator<LauncherHolder>{

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }

}
