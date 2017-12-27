package base.core.cache;

/**
 * 缓存接口
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public interface Cache {

    /**
     * 放入数据
     *
     * @param key
     * @param value
     */
    public void put(String key, long value);

    /**
     * 放入数据和缓存时间
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    public void put(String key, long value, int cacheTime);

    /**
     * 放入数据
     *
     * @param key
     * @param value
     */
    public void put(String key, String value);

    /**
     * 放入数据和缓存时间
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    public void put(String key, String value, int cacheTime);

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public String getString(String key);

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public long getLong(String key);


    /**
     * 删除缓存数据
     *
     * @param key
     */
    public void remove(String key);


    /**
     * 清除缓存
     */
    public void clear();
}
