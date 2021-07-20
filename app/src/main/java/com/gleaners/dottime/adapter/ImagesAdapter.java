package com.gleaners.dottime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.gleaners.dottime.R;
import com.gleaners.dottime.beans.Image;
import com.gleaners.dottime.utils.GlideUtils;

import java.util.List;

/**
 * @author ...
 * @date 2021-07-19 9:16
 * description：
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> list;
    private Context context;
    private OnItemClickListener listener;

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

        ImageView itemImage = holder.imageView;

        GlideUtils.loadImage(context, list.get(position).getPath(), itemImage, true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(list.get(position), itemImage);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            int width = ScreenUtils.getScreenWidth() / 4;
            itemView.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Image image, ImageView itemImageView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
