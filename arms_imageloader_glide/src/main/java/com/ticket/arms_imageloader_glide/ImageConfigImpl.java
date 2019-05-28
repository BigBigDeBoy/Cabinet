package com.ticket.arms_imageloader_glide;

import android.widget.ImageView;

import com.ticket.arms.http.BaseUrl;
import com.ticket.arms.http.imageloader.ImageConfig;

/**
 *  这里存放图片请求的配置信息,可以一直扩展字段,如果外部调用时想让图片加载框架
 *  做一些操作,比如清除缓存或者切换缓存策略,则可以定义一个 int 类型的变量,内部根据 switch(int) 做不同的操作
 *  其他操作同理
 */
public class ImageConfigImpl extends ImageConfig {

    private int cacheStrategy;
    private int fallback;
    private int imageRadius;
    private int blurValue;

    private ImageView[] imageViews;
    private boolean isCrossFade;
    private boolean isCenterCrop;
    private boolean isCircle;
    private  boolean isClearMemory;
    private boolean isClearDiskCache;

    public ImageConfigImpl(Builder builder) {
       this.url=builder.url;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPic = builder.errorPic;
        this.fallback = builder.fallback;
        this.cacheStrategy = builder.cacheStrategy;
        this.imageRadius = builder.imageRadius;
        this.blurValue = builder.blurValue;
        this.imageViews = builder.imageViews;
        this.isCrossFade = builder.isCrossFade;
        this.isCenterCrop = builder.isCenterCrop;
        this.isCircle = builder.isCircle;
        this.isClearMemory = builder.isClearMemory;
        this.isClearDiskCache = builder.isClearDiskCache;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getImageRadius() {
        return imageRadius;
    }

    public int getBlurValue(){
        return blurValue;
    }


    public static Builder builder(){
        return new Builder();
    }

    public boolean isImageRadius() {
        return imageRadius>0;
    }

    public boolean isBlurImage() {
        return blurValue>0;
    }

    public int getFallback() {
        return fallback;
    }

    public ImageView[] getImageViews() {
        return imageViews;
    }

    public boolean isClearDiskCache() {
        return isClearDiskCache;
    }

    public boolean isClearMemory() {
        return isClearMemory;
    }


    public static final class Builder{
        private String url;
        private ImageView imageView;
        private int placeholder;
        private int errorPic;
        private int fallback;
        private int cacheStrategy;
        private int imageRadius;
        private int blurValue;
        private ImageView[] imageViews;
        private boolean isCrossFade;
        private boolean isCenterCrop;
        private boolean isCircle;
        private boolean isClearMemory;
        private boolean isClearDiskCache;

        public Builder( ) {
        }

        public Builder url(String url){
            this.url=url;
            return this;
        }

        public Builder placeholder(int placeholder){
            this.placeholder=placeholder;
            return this;
        }

        public Builder errorPic(int errorPic) {
            this.errorPic = errorPic;
            return this;
        }

        public Builder fallback(int fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder cacheStrategy(int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder imageRadius(int imageRadius) {
            this.imageRadius = imageRadius;
            return this;
        }

        public Builder blurValue(int blurValue) { //blurValue 建议设置为 15
            this.blurValue = blurValue;
            return this;
        }


        public Builder imageViews(ImageView... imageViews) {
            this.imageViews = imageViews;
            return this;
        }

        public Builder isCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder isCenterCrop(boolean isCenterCrop) {
            this.isCenterCrop = isCenterCrop;
            return this;
        }

        public Builder isCircle(boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        public Builder isClearMemory(boolean isClearMemory) {
            this.isClearMemory = isClearMemory;
            return this;
        }

        public Builder isClearDiskCache(boolean isClearDiskCache) {
            this.isClearDiskCache = isClearDiskCache;
            return this;
        }

        public ImageConfigImpl builder(){
            return new ImageConfigImpl(this);
        }


        public ImageConfigImpl build() {
            return new ImageConfigImpl(this);
        }
    }
}
