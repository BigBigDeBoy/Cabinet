package com.ticket.arms.http.imageloader;

import android.content.Context;
import android.support.annotation.Nullable;

/**
 * 图片加载策略,实现 {@link BaseImageLoaderStrategy}
 * 并通过 {@link ImageLoader#setLoadImgStrategy(BaseImageLoaderStrategy)} 配置后,才可进行图片请求
 * @param <T>
 */
public interface BaseImageLoaderStrategy<T extends ImageConfig> {

    void loadImage(@Nullable Context context, @Nullable T config);

    void clear(@Nullable Context ctx, @Nullable T config);
}
