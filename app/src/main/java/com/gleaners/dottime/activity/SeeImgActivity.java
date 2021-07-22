package com.gleaners.dottime.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.BarUtils;
import com.gleaners.dottime.R;
import com.gleaners.dottime.adapter.ViewPagerAdapter;
import com.gleaners.dottime.base.BaseActivity;
import com.gleaners.dottime.beans.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ...
 * @date 2021-07-19 16:12
 * description：
 */
public class SeeImgActivity extends BaseActivity {

    @BindView(R.id.viewPager2)
    ViewPager2 viewPager2;

    private Image image;

    private List<Image> list;
    private int position;

    @Override
    protected int onCreateLayout() {
        return R.layout.activity_see_img;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(this, Color.parseColor("#65000000"));
//        BarUtils.setNavBarColor(this, Color.parseColor("#65000000"));
//        BarUtils.setNavBarLightMode(this, true);
        //停止动画
//        postponeEnterTransition();
        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("list");
        position = intent.getIntExtra("position", -1);

//        GlideUtils.loadImage(this, image.getPath(), imageView, new GlideUtils.ImageLoadFinishCallback() {
//            @Override
//            public void onLoadFinish() {
//                //开始动画
//                startPostponedEnterTransition();
//            }
//        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(list, this);
        viewPager2.setAdapter(adapter);
        viewPager2.setCurrentItem(position, false);
        startPostponedEnterTransition();
        adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boolean barVisible = BarUtils.isStatusBarVisible(SeeImgActivity.this);
                BarUtils.setStatusBarVisibility(SeeImgActivity.this, !barVisible);
                BarUtils.setNavBarVisibility(SeeImgActivity.this, !barVisible);
            }
        });
    }

    @OnClick(R.id.viewPager2)
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.viewPager2:

                break;
        }
    }
}
