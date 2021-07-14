package com.gleaners.dottime.fragment;

import androidx.fragment.app.Fragment;

import com.gleaners.dottime.R;
import com.gleaners.dottime.base.BaseFragment;

/**
 * @author ...
 * @date 2021-07-14 16:19
 * description：
 */
public class AlbumsFragment extends BaseFragment {

    public static BaseFragment getInstance() {
        return new AlbumsFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_albums;
    }

    @Override
    protected void initView() {

    }
}
