package com.linxz.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.linxz.latte.app.ConfigKeys;
import com.linxz.latte.app.Latte;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String host= Latte.getConfiguration(ConfigKeys.API_HOST.name());
        Toast.makeText(this,host,Toast.LENGTH_LONG).show();
    }
}
