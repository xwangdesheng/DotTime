package com.gleaners.dottime.fragment;

import com.gleaners.dottime.R;
import com.gleaners.dottime.base.BaseFragment;

/**
 * @author ...
 * @date 2021-07-14 16:19
 * description：
 */
public class ImagesFragment extends BaseFragment {

    public static BaseFragment getInstance() {
        return new ImagesFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_images;
    }

    @Override
    protected void initView() {

    }
}
