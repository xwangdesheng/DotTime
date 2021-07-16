package com.gleaners.dottime.fragment;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.gleaners.dottime.R;
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

/**
 * @author ...
 * @date 2021-07-14 16:19
 * description：
 */
public class ImagesFragment extends BaseFragment {

    @Override
    protected int onCreateLayout() {
        return R.layout.fragment_images;
    }

    @Override
    protected void onCreateViewInit(Bundle savedInstanceState) {
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
        Log.d(TAG, "loadImages: ");
        ImageUtils.loadImageForSDCard(mActivity, new ImageUtils.CallBack() {
            @Override
            public void onSuccess(ArrayList<Folder> folders, ArrayList<Image> images) {
                Log.d(TAG, "folders: " + folders);
                Log.d(TAG, "images: " + images);
            }
        });
    }
}
