package com.sm_arts.jibcon.utils.bindingadapter;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by jaeyoung on 8/12/17.
 */

public class ImageViewBindingAdapter {
    private static final String TAG = "ImageViewBindingAdapter";

    @BindingAdapter("android:src")
    public static void setImageWithResourceId(ImageView imageView, int resourceId) {
//        Log.d(TAG, "setImageWithResourceId() called with: imageView = [" + imageView + "]," +
//                " resourceId = [" + resourceId + "]");

        imageView.setImageResource(resourceId);
    }
}
