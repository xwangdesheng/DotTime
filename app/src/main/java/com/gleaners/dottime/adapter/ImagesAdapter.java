package com.gleaners.dottime.adapter;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.gleaners.dottime.R;
import com.gleaners.dottime.beans.Image;
import com.gleaners.dottime.utils.GlideUtils;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.File;
import java.util.List;

import static android.content.Context.WINDOW_SERVICE;

/**
 * @author ...
 * @date 2021-07-19 9:16
 * description：
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> list;
    private Context context;

    public ImagesAdapter(List<Image> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUtils.loadImage(context, list.get(position).getPath(), holder.imageView, true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            int width = ScreenUtils.getScreenWidth() / 4;
            itemView.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        }
    }
}
