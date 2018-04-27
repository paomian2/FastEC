package com.linxz.latte.ec.icons.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.linxz.latte.ec.R;
import com.linxz.latte.ec.R2;

import butterknife.BindView;


/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月05日14:48  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class MyClass extends Activity {

     @BindView(R2.id.tvTest)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myclass);
        textView.setText("dfafdaf");
    }
}
