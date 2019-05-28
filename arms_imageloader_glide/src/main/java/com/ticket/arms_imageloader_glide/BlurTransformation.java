package com.ticket.arms_imageloader_glide;

import android.graphics.Bitmap;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.ticket.arms.utils.FastBlur;

import java.security.MessageDigest;

/**
 * @author :dadade
 * date   :2019-05-27 19:05
 * desc   :高斯模糊
 */
public class BlurTransformation extends BitmapTransformation {

    private static final String ID = BlurTransformation.class.getName();
    private static final byte[] ID_BYTES = ID.getBytes(Key.CHARSET);
    public static final int DEFAULT_RADIUS = 15;
    private int mRadius = DEFAULT_RADIUS;

    public BlurTransformation(@IntRange(from = 0) int radius) {
        mRadius = radius;
    }


    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return FastBlur.doBlur(toTransform, mRadius, true);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof BlurTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
