package com.gleaners.dottime.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.gleaners.dottime.R;

import java.io.File;

public class GlideUtils {
    public static void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.icon_image_background)
                .error(R.mipmap.icon_image_background)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).asBitmap().apply(options).load(url).into(imageView);
    }

    public static void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, boolean isCenterCrop) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.color.gray_400)
                .error(R.color.gray_400)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        if (isCenterCrop) {
            options = options.centerCrop();
        } else {
            options = options.optionalFitCenter();
        }
        Glide.with(context).asBitmap().apply(options).load(url).dontAnimate().into(imageView);
    }

    public static void loadImage(@NonNull Context context, @DrawableRes int resId, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerInside()
                .placeholder(R.mipmap.icon_image_background)
                .error(R.mipmap.icon_image_background)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context).asBitmap().apply(options).load(resId).into(imageView);
    }

    public static void loadImage(@NonNull Context context, String path, @NonNull ImageView imageView, ImageLoadFinishCallback callback) {
        Glide.with(context).load(new File(path)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                callback.onLoadFinish();
                return false;
            }
        }).into(imageView);
    }

    public interface ImageLoadFinishCallback{
        void onLoadFinish();
    }

}
