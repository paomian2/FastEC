package com.linxz.fastec;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.linxz.latte.activitys.ProxyActivity;
import com.linxz.latte.app.Latte;
import com.linxz.latte.delegates.LatteDelegate;
import com.linxz.latte.ec.launcher.LaunchScrollDelegate;
import com.linxz.latte.ec.launcher.LauncherDelegate;
import com.linxz.latte.ec.main.EcBottomDelegate;
import com.linxz.latte.ec.sign.ISignListener;
import com.linxz.latte.ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }
}
