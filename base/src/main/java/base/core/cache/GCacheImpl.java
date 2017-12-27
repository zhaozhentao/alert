package base.core.cache;

import java.io.File;

import io.ganguo.app.gcache.CacheBuilder;
import io.ganguo.app.gcache.Config;
import io.ganguo.app.gcache.interfaces.GCache;
import io.ganguo.app.gcache.transcoder.StringTranscoder;

/**
 * GCache实现
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public class GCacheImpl implements Cache {
    /**
     * GCache
     */
    private GCache mCache;

    public GCacheImpl(File cacheRootDirectory) {
        // 配置缓存
        mCache = CacheBuilder.newBuilder()
                .withTranscoder(new StringTranscoder())
                .withCacheRootDirectory(cacheRootDirectory)
                .maxDiskUsageBytes(15 * Config.BYTES_MB)
                .maxMemoryUsageBytes(3 * Config.BYTES_MB)
                .defaultCacheTime(Integer.MAX_VALUE)
                .build();
    }

    /**
     * 放入数据
     *
     * @param key
     * @param value
     */
    @Override
    public void put(String key, long value) {
        mCache.put(key, value + "");
    }

    /**
     * 放入数据和缓存时间
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    @Override
    public void put(String key, long value, int cacheTime) {
        mCache.put(key, value + "", cacheTime);
    }

    /**
     * 放入数据
     *
     * @param key
     * @param value
     */
    @Override
    public void put(String key, String value) {
        mCache.put(key, value);
    }

    /**
     * 放入数据和缓存时间
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    @Override
    public void put(String key, String value, int cacheTime) {
        mCache.put(key, value, cacheTime);
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        return mCache.get(key);
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    @Override
    public long getLong(String key) {
        String lon = getString(key);
        if (lon != null) {
            return Long.valueOf(lon);
        }
        return 0;
    }

    /**
     * 删除缓存数据
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        mCache.remove(key);
    }

    /**
     * 清除缓存
     */
    @Override
    public void clear() {
        mCache.clear();
    }
}
