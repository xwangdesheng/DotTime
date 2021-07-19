package com.gleaners.dottime.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.gleaners.dottime.beans.Folder;
import com.gleaners.dottime.beans.Image;
import android.provider.MediaStore.Images.Media;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ...
 * @date 2021-07-16 16:41
 * description：
 */
public class ImageUtils {
    private static String TAG = ImageUtils.class.getSimpleName();

    /**
     * loading image from SDCard.
     */
    public static void loadImageForSDCard(final Context context, final CallBack callBack) {

        // Because of scanning image would take long time, do it at child thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = context.getContentResolver();
                Cursor cursor = contentResolver.query(imgUri, new String[]{
                        Media.DATA,
                        Media.DISPLAY_NAME,
                        Media.DATE_ADDED,
                        Media._ID,
                }, null, null, Media.DATE_ADDED);

                ArrayList<Image> images = new ArrayList<>();

                //Read image of scanned.
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        //获取图片路径
                        String path = cursor.getString(cursor.getColumnIndex(Media.DATA));
                        //获取图片名字
                        String name = cursor.getString(cursor.getColumnIndex(Media.DISPLAY_NAME));
                        //获取图片时间
                        long time = cursor.getLong(cursor.getColumnIndex(Media.DATE_ADDED));
                        images.add(new Image(path, time, name));
                    }
                }
//                images.add(new Image(true));
                if (cursor != null) {
                    cursor.close();
                }
                Collections.reverse(images);
                callBack.onSuccess(splitFolder(images), images);
            }
        }).start();
    }

    /**
     * dismantling image by folder, first folder is used to keeping all images.
     */
    private static ArrayList<Folder> splitFolder(ArrayList<Image> images) {
        ArrayList<Folder> folders = new ArrayList<>();
        if (images != null && !images.isEmpty()) {
            int size = images.size();
            for (int i = 0; i < size; i++) {
                String path = images.get(i).getPath();
                String name = getFolderName(path);
                if (!name.equals("")) {
                    Folder folder = getFolder(name, folders);
                    folder.addImage(images.get(i));
                }
            }
        }
        return folders;
    }

    //根据图片路径，获取图片文件夹名称
    private static String getFolderName(String path) {
        if (path != null) {
            String[] strings = path.split(File.separator);
            if (strings.length >= 2) {
                return strings[strings.length - 2];
            }
        }
        return "";
    }

    private static Folder getFolder(String name, List<Folder> folders) {
        if (folders != null && !folders.isEmpty()) {
            int size = folders.size();
            for (int i = 0; i < size; i++) {
                Folder folder = folders.get(i);
                if (name.equals(folder.getName())) {
                    return folder;
                }
            }
        }
        Folder newFolder = new Folder(name);
        folders.add(newFolder);
        return newFolder;
    }

    public interface CallBack {
        void onSuccess(ArrayList<Folder> folders, ArrayList<Image> images);
    }
}
