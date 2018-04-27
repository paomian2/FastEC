package com.linxz.latte.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.linxz.latte.app.Latte;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月08日17:35  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class DimeUtils {

    /**获取屏幕的宽度*/
    public static int getScreenWidth(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**获取屏幕的高度*/
    public static int getScreenHeight(){
        final Resources resources=Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
