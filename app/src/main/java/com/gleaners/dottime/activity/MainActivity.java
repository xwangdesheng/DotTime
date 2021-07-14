package com.gleaners.dottime.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gleaners.dottime.R;
import com.gleaners.dottime.base.BaseActivity;
import com.gleaners.dottime.fragment.AlbumsFragment;
import com.gleaners.dottime.fragment.ImagesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initFragment();
        initBottomNavigation();

        setFragment(0);
    }

    private void initFragment() {
        fragments.add(ImagesFragment.getInstance());
        fragments.add(AlbumsFragment.getInstance());
    }


    private void initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_images:
                        setFragment(0);
                        break;
                    case R.id.menu_albums:
                        setFragment(1);
                        break;
                }
                return false;
            }
        });
    }


    private void setFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.frameLayout, fragments.get(position));
        transaction.commit();
    }
}