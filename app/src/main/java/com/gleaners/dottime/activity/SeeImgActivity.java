package com.gleaners.dottime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gleaners.dottime.R;
import com.gleaners.dottime.base.BaseActivity;
import com.gleaners.dottime.beans.Image;
import com.gleaners.dottime.utils.GlideUtils;

import java.io.File;

import butterknife.BindView;

/**
 * @author ...
 * @date 2021-07-19 16:12
 * description：
 */
public class SeeImgActivity  extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    private Image image;

    @Override
    protected int onCreateLayout() {
        return R.layout.activity_see_img;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        image = (Image) intent.getSerializableExtra("imageObj");
    }
}
