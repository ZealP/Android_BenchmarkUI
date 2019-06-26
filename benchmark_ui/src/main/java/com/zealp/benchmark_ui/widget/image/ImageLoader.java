package com.zealp.benchmark_ui.widget.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zealp.benchmark_ui.utils.GlideApp;
import com.zealp.benchmark_ui.utils.GlideRequest;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 采用Glide图片加载 图片处理 图片缓存
 *
 * Author: ZealP
 * Created On: 2019/2/17
 */
public class ImageLoader {

    //单例
    private static ImageLoader mImageLoader = null;

    public ImageLoader() {
    }

    /**
     * 获取单例
     *
     * @param
     * @return ImageLoader
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public static ImageLoader getInstace() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader();
        }
        return mImageLoader;
    }

    /**
     * 调用加载图片方法 -- 基本图片加载功能
     *
     * @param context,imageView,imgUrl
     * @return void
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void loadImg(Context context, ImageView imageView, String imgUrl) {
        load(context, imageView, imgUrl, null, null, false, false, false, 0);
    }

    /**
     * 调用加载图片方法 -- 是否切成圆形
     *
     * @param context,imageView,imgUrl
     * @return void
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void loadCircleImg(Context context, ImageView imageView, String imgUrl) {
        load(context, imageView, imgUrl, null, null, true, false, false, 0);
    }

    /**
     * 调用加载图片方法 -- 是否切成圆角，需提供圆角半径
     *
     * @param context,imageView,imgUrl
     * @return void
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void loadRoundedCornersImg(Context context, ImageView imageView, String imgUrl, int radius) {
        load(context, imageView, imgUrl, null, null, false, true, false, radius);
    }

    /**
     * 调用加载图片方法 -- 是否加载为灰度图片
     *
     * @param context,imageView,imgUrl
     * @return void
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void loadGrayscaleImg(Context context, ImageView imageView, String imgUrl) {
        load(context, imageView, imgUrl, null, null, false, false, true, 0);
    }

    /**
     * 加载图片方法
     *
     * @param context          上下文
     * @param imageView        图片控件
     * @param imgUrl           图片地址
     * @param defaultImage     默认图片
     * @param errorImage       错误时图片
     * @param isCropCircle     是否切成圆形
     * @param isRoundedCorners 是否切圆角 默认30
     * @param radius           圆角半径
     * @param isGrayscale      是否做灰度处理
     * @return void
     * <p>
     * tip: .diskCacheStrategy(DiskCacheStrategy strategy).设置缓存策略。
     * DiskCacheStrategy.SOURCE：缓存原始数据
     * DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据
     * DiskCacheStrategy.NONE：什么都不缓存
     * DiskCacheStrategy.ALL：缓存SOURC和RESULT
     * 默认采用DiskCacheStrategy.RESULT策略
     * 对于download only操作要使用DiskCacheStrategy.SOURCE。
     * <p>
     * .priority(Priority priority). 指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。
     * 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
     * 默认为Priority.NORMAL。
     * <p>
     * .skipMemoryCache(boolean skip). 设置是否跳过内存缓存，但不保证一定不被缓存
     * （比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
     * <p>
     * .override(int width, int height). 重新设置Target的宽高值（单位为pixel）。
     * <p>
     * .into(int width, int height). 后台线程加载时要加载资源的宽高值（单位为pixel）。
     * <p>
     * .preload(int width, int height). 预加载resource到缓存中（单位为pixel）。
     * <p>
     * .asBitmap(). 无论资源是不是gif动画，都作为Bitmap对待。如果是gif动画会停在第一帧。
     * <p>
     * .asGif().把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()
     * <p>
     * Glide.thumbnail(0.1f) 用原图的1/10作为缩略图
     * <p>
     * DrawableRequestBuilder<Integer> thumbnailRequest = Glide //用其它图片作为缩略图
     * .with(this)
     * .load(R.drawable.xxx);
     * //then
     * Glide.thumbnail(thumbnailRequest)
     * <p>
     * 更多API查看官方文档 https://github.com/bumptech/glide
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void load(Context context, ImageView imageView, String imgUrl, Drawable defaultImage,
                     Drawable errorImage, boolean isCropCircle, boolean isRoundedCorners, boolean isGrayscale, int radius) {

        //处理选项设置和开始一般resource类型资源的加载

        GlideRequest genericRequestBuilder = null;

        // 是否切成圆形
        if (isCropCircle) {
            genericRequestBuilder = GlideApp.with(context).load(imgUrl).transform(new CropCircleTransformation(context));
        }
        //是否切成圆角
        if (isRoundedCorners) {
            genericRequestBuilder = GlideApp.with(context).load(imgUrl)
                    .transform(new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL));
        }
        //是否做灰度处理
        if (isGrayscale) {
            genericRequestBuilder = GlideApp.with(context).load(imgUrl)
                    .transform(new GrayscaleTransformation(context));
        }
        //不做glide-transformations处理
        if (!isCropCircle && !isRoundedCorners && !isGrayscale) {
            genericRequestBuilder = GlideApp.with(context).load(imgUrl);
        }

        //处理选项，加载图片
        if (genericRequestBuilder == null) {
            return;
        }
        genericRequestBuilder.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(defaultImage)
                .priority(Priority.NORMAL)
                .error(errorImage)
                .into(imageView);
    }

    /**
     * 清除内存缓存
     *
     * @param context
     * @return
     * @author leibing
     * @createTime 2016/8/15
     * @lastModify 2016/8/15
     */
    public void clearMemory(Context context) {
        // 图片加载库采用Glide框架
        // 必须在UI线程中调用
        Glide.get(context).clearMemory();

    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     * @return
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void clearDiskCache(Context context) {
        // 必须在后台线程中调用，建议同时clearMemory()
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清除view缓存
     *
     * @param view
     * @return
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public void clearViewCache(View view) {
//        GlideApp..clear(view);
    }

    /**
     * 获取SD卡下图片路径
     *
     * @param fullPath SD下图片完整路径
     * @return
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public static String getSDSource(String fullPath) {
        return "file://" + fullPath;
    }

    /**
     * 获取ASSETS下图片路径
     *
     * @param fileName 图片名称
     * @return
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public static String getAssetsSource(String fileName) {
        return "file:///android_asset/" + fileName;
    }

    /**
     * 获取Raw下视频可以解析一张图片路径
     *
     * @param context 上下文
     * @param rawRid  视频id
     * @return
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public static String getRawSource(Context context, int rawRid) {
        return "android.resource://" + context.getPackageName() + "/raw/" + rawRid;
    }

    /**
     * 获取图片资源文件
     *
     * @param context 上下文
     * @param drawRid drawable目录下图片id
     * @return
     * @author miao
     * @createTime 2017年2月18日
     * @lastModify 2017年2月18日
     */
    public static String getDrawableSource(Context context, int drawRid) {
        return "android.resource://" + context.getPackageName() + "/drawable/" + drawRid;
    }

}