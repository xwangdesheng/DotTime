package com.gleaners.dottime.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gleaners.dottime.R;
import com.gleaners.dottime.activity.SeeImgActivity;
import com.gleaners.dottime.adapter.ImagesAdapter;
import com.gleaners.dottime.base.BaseFragment;
import com.gleaners.dottime.beans.Folder;
import com.gleaners.dottime.beans.Image;
import com.gleaners.dottime.utils.ImageUtils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ...
 * @date 2021-07-14 16:19
 * description：
 */
public class ImagesFragment extends BaseFragment {
    @BindView(R.id.img_recyclerView)
    RecyclerView recyclerView;

    private final static int WHAT_NOTIFY_DATA = 0;

    private List<Image> list;
    private ImagesAdapter adapter;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case WHAT_NOTIFY_DATA:
                    adapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    @Override
    protected int onCreateLayout() {
        return R.layout.fragment_images;
    }

    @Override
    protected void onCreateViewInit(Bundle savedInstanceState) {
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 4));
        adapter = new ImagesAdapter(list, mActivity);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImagesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Image image, int position, ImageView imageView) {
                Intent intent = new Intent(mActivity, SeeImgActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
                bundle.putInt("position", position);
                intent.putExtras(bundle);

                Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        mActivity,
                        imageView,
                        "viewPager"
                ).toBundle();
                ActivityCompat.startActivity(mActivity, intent, options);
            }
        });

        PermissionX.init(this)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .onExplainRequestReason(new ExplainReasonCallback() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList) {
                        String message = "点滴时光需要您同意以下权限才能正常使用";
                        scope.showRequestReasonDialog(deniedList, message, "确定", "取消");
                    }
                })
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            loadImages();
                        }
                    }
                });
    }

    private void loadImages() {
        ImageUtils.loadImageForSDCard(mActivity, new ImageUtils.CallBack() {
            @Override
            public void onSuccess(ArrayList<Folder> folders, ArrayList<Image> images) {
                Log.d(TAG, "folders: " + folders);
                Log.d(TAG, "images: " + images);
                list.addAll(images);
                handler.sendEmptyMessage(WHAT_NOTIFY_DATA);
            }
        });
    }
}
