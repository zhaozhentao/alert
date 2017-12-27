package base.core.http.request;


import base.core.event.OnEventListener;
import base.core.http.HttpConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求基类
 * <p/>
 * Created by Tony on 10/18/14.
 */
public abstract class Request {
    /**
     * URL
     */
    protected String url;

    /**
     * Method
     */
    protected HttpMethod method;

    /**
     * 请求的头部
     */
    protected Map<String, String> headers = new HashMap<String, String>();

    /**
     * 缓存时间
     */
    protected int cacheTime = 0;

    /**
     * 超时时间
     */
    protected int timeout = HttpConstants.REQUEST_TIMEOUT_MS;

    /**
     * 编码
     */
    protected String paramsEncoding = HttpConstants.DEFAULT_PARAMS_ENCODING;

    protected OnEventListener<Boolean> onEventListener;

    public Request(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    /**
     * HTTP BODY
     * 处理HTTP中的主体内容
     * 包括POST和PUT参数，以及一些主体内容
     *
     * @return body
     */
    public abstract byte[] getBody();

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getParamsEncoding() {
        return paramsEncoding;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public void setCancelListener(OnEventListener<Boolean> onEventListener) {
        this.onEventListener = onEventListener;
    }

    public void cancel() {
        if (onEventListener != null) {
            onEventListener.onEvent(true);
        }
    }

}
