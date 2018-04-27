package com.linxz.latte.delegates.bottom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.linxz.latte.delegates.LatteDelegate;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月27日10:59  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener{

    private long mExitTime=0;
    private static final int EXIT_TIME=2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView=getView();
        if (rootView!=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
             if (System.currentTimeMillis()-mExitTime>EXIT_TIME){
                 Toast.makeText(getContext(),"双击退出"+getString(com.wang.avi.R.string.app_name),Toast.LENGTH_SHORT).show();
                 mExitTime=System.currentTimeMillis();
             }else{
                 if (mExitTime!=0){
                     mExitTime=0;
                 }
                 _mActivity.finish();
             }
        }
        return false;
    }
}
