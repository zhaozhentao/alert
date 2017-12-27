package base.core.http.api;

import android.text.TextUtils;
import android.util.Log;

import base.core.cache.Cache;
import base.core.cache.CacheManager;
import base.core.http.HttpConstants;
import base.core.http.response.ApiResponse;
import base.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络服务抽象类
 * <p/>
 * 缓存的实现
 * <p/>
 * 重复响应控制
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class AbstractHttpService implements HttpService {
    private static final String TAG = AbstractHttpService.class.getName();
    private Map<String, String> headers = new HashMap<String, String>();
    private Cache mCache;

    public AbstractHttpService() {
        mCache = CacheManager.getInstance();
    }

    /**
     * 获取请求中的头部信息包
     *
     * @return
     */
    public Map<String, String> getHeaderMap() {
        if (HttpConstants.DEBUG) Log.d(TAG, "request headers: " + headers.toString());
        return headers;
    }

    /**
     * 添加请求头信息
     *
     * @param key
     * @param value
     */
    @Override
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    /**
     * 缓存响应
     * 存在 true
     *
     * @param url
     * @param httpListener
     * @return
     */
    public boolean fireCache(String url, HttpListener<?> httpListener) {
        String cacheContent = mCache.getString(url);
        if (!TextUtils.isEmpty(cacheContent)) {
            httpListener.handleResponse(new ApiResponse(0, cacheContent));
            if (HttpConstants.DEBUG)
                Log.d(TAG, " request: " + url + " FoundCache: " + cacheContent);
            return true;
        }
        return false;
    }

    /**
     * 放到缓存中
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    public void putCache(String key, String value, int cacheTime) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value) && cacheTime > 0) {
            mCache.put(key, value, cacheTime);
        }
    }

}