package com.gleaners.dottime.fragment;

import com.gleaners.dottime.R;
import com.gleaners.dottime.base.BaseFragment;

/**
 * @author ...
 * @date 2021-07-14 16:20
 * description：
 */
public class NotesFragment extends BaseFragment {

    public static BaseFragment getInstance() {
        return new NotesFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_notes;
    }

    @Override
    protected void initView() {

    }
}
