package com.linxz.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.linxz.latte.R;
import com.linxz.latte.utils.dimen.DimeUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * <p>
 * Function： 加载框
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月08日17:39  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class LatteLoader {

    private static final int LOADER_SIZE_SCARE = 8;
    private static final int LOADER_OFFSET_SCARE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAULT_LOADER = LoaderStyle.BallPulseIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> styleEnum){
        showLoading(context,styleEnum.name());
    }

    public static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LatteLoaderCreator.create(context, type);
        dialog.setContentView(avLoadingIndicatorView);

        int screenWidth = DimeUtils.getScreenWidth();
        int screenHeight = DimeUtils.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = screenWidth / LOADER_SIZE_SCARE;
            lp.height = screenHeight / LOADER_OFFSET_SCARE;
            //偏移量
            lp.height = lp.height + screenHeight / LOADER_OFFSET_SCARE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
        }
    }
}
