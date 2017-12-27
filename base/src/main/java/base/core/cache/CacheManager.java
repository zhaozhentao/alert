package base.core.cache;

import android.content.Context;

/**
 * 缓存管理类
 *
 * Created by zhihui_chen on 14-8-14.
 */
public class CacheManager {
    private static Context context = null;
    private static Cache mCache = null;

    public static void register(Context context) {
        CacheManager.context = context;
    }

    public static Cache getInstance() {
        if(mCache == null) {
            mCache = new GCacheImpl(context.getCacheDir());
        }
        return mCache;
    }
}
