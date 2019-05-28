package com.ticket.arms.http.imageloader;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ticket.arms.utils.Preconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *  {@link ImageLoader} 使用策略模式和建造者模式,可以动态切换图片请求框架(比如说切换成 Picasso )
 *  当需要切换图片请求框架或图片请求框架升级后变更了 Api 时
 *  这里可以将影响范围降到最低,所以封装 {@link ImageLoader} 是为了屏蔽这个风险
 */
@Singleton
public class ImageLoader {

    @Inject
    @org.jetbrains.annotations.Nullable
    public ImageLoader() {
    }

    @Inject
    @Nullable
    BaseImageLoaderStrategy mStrategy;

    @Nullable
    public BaseImageLoaderStrategy getLoadImgStrategy() {
        return mStrategy;
    }

    public <T extends ImageConfig> void loadImage(Context context, T config) {
        Preconditions.checkNotNull(mStrategy, "Please implement BaseImageLoaderStrategy and call GlobalConfigModule.Builder#imageLoaderStrategy(BaseImageLoaderStrategy) in the applyOptions method of ConfigModule");
        this.mStrategy.loadImage(context, config);
    }

    public <T extends ImageConfig> void clear(Context context, T config) {
        Preconditions.checkNotNull(context, config);
        this.mStrategy.clear(context, config);
    }

    /**
     * 可在运行时随意切换 {@link BaseImageLoaderStrategy}
     *
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        Preconditions.checkNotNull(strategy, "strategy == null");
        this.mStrategy = strategy;
    }
}
