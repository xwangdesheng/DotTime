package com.gleaners.dottime.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author ...
 * @date 2021-07-14 16:40
 * description：
 */
public abstract class BaseActivity extends AppCompatActivity {

    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
