package com.gleaners.dottime.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * @author ...
 * @date 2021-07-14 16:45
 * description：
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();

    protected View contentView;
    protected BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(onCreateLayout(), container, false);
        ButterKnife.bind(this, contentView);
        onCreateViewInit(savedInstanceState);
        return contentView;
    }

    protected abstract int onCreateLayout();

    protected abstract void onCreateViewInit(Bundle savedInstanceState);
}
