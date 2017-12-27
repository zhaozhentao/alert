package base.core.image;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.view.View;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;

import base.R;
import base.utils.StringUtils;

/**
 * Created by Tony on 11/24/14.
 */
public class GImageLoader extends ImageLoader {

    private volatile static GImageLoader instance;

    /**
     * Returns singleton class instance
     */
    public static GImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new GImageLoader();
                }
            }
        }
        return instance;
    }

    protected GImageLoader() {

    }

    public final static String IMAGE_CACHE_PATH = "imageCache";
    public final static String DATA_PATH = "yidianting";

    public static String getDataPath(Context context) {
        // 判断是否挂载了SD卡
        String dataPath = null;
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            dataPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + DATA_PATH
                + File.separator;
        } else {
            File basePath = context.getFilesDir();
            if (basePath == null) {
                basePath = context.getCacheDir();
            }
            dataPath = basePath.getAbsolutePath()
                + File.separator
                + DATA_PATH
                + File.separator;
        }
        File file = new File(dataPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dataPath;
    }

    public static String getImageCachePath(Context context) {
        String images = getDataPath(context) + IMAGE_CACHE_PATH + File.separator;
        File fileDir = new File(images);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return images;
    }

    /**
     * init configuration
     *
     * @param application
     */
    public void init(Context application) {
        int memClass = ((ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass / 8;  // 1/8 of system ram

        DisplayImageOptions defaultOptions = new DisplayImageOptions
            .Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new FadeInBitmapDisplayer(600))
//                .showImageOnLoading(R.drawable.ic_default_pic)
            .build();

        // File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
            .Builder(application.getApplicationContext())
            .threadPoolSize(2)
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new LruMemoryCache(cacheSize))
            .memoryCacheSize(cacheSize)
            .diskCache(new UnlimitedDiskCache(new File(getImageCachePath(application)))) // default
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .defaultDisplayImageOptions(defaultOptions)
            .build();
        super.init(configuration);

        L.writeLogs(false);
    }

    /**
     * 解决ImageLoader重复显示同一个图片问题
     *
     * @param uri
     * @param imageAware
     * @param options
     * @param listener
     * @param progressListener
     */
    @Override
    public void displayImage(String uri, ImageAware imageAware, DisplayImageOptions options,
                             ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {

        if (StringUtils.isEmpty(uri)) {
            return;
        }
        if (imageAware == null || imageAware.getWrappedView() == null) {
            super.displayImage(uri, imageAware, options, listener, progressListener);
            return;
        }

        View view = imageAware.getWrappedView();
        if (view != null && !StringUtils.equals(uri, view.getTag(R.string.g_image_loader_uri) + "")) {

            super.displayImage(uri, imageAware, options, listener, progressListener);

            view.setTag(R.string.g_image_loader_uri, uri);
        }
    }

    /**
     * 解决ImageLoader重复显示同一个图片问题
     *
     * @param uri
     * @param imageAware
     */
    @Override
    public void displayImage(String uri, ImageAware imageAware, DisplayImageOptions options) {

        if (StringUtils.isEmpty(uri)) {
            return;
        }
        if (imageAware == null || imageAware.getWrappedView() == null) {
            super.displayImage(uri, imageAware, options);
            return;
        }

        View view = imageAware.getWrappedView();
        if (view != null && !StringUtils.equals(uri, view.getTag(R.string.g_image_loader_uri) + "")) {

            super.displayImage(uri, imageAware, options);

            view.setTag(R.string.g_image_loader_uri, uri);
        }
    }

}
