package com.gleaners.dottime.base;

import android.content.Context;
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

    protected String TAG = getClass().getSimpleName();
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onCreateLayout());
        ButterKnife.bind(this);
        context = this;
        onCreateView(savedInstanceState);
    }

    protected abstract int onCreateLayout();

    protected abstract void onCreateView(Bundle savedInstanceState);

}
