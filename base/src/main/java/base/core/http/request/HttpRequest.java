package base.core.http.request;

import base.core.http.HttpUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP请求
 * url, method
 * headerMap,  postParams
 * <p/>
 * Created by Tony on 10/16/14.
 */
public class HttpRequest extends Request {

    /**
     * 请求参数
     */
    private Map<String, String> requestParams = new HashMap<String, String>();
    private Map<String, Integer[]> requestParamsInt = new HashMap<>();

    public HttpRequest(String url, HttpMethod method) {
        super(url, method);
    }

    /**
     * HTTP BODY
     * 处理HTTP中的主体内容
     * 包括POST和PUT参数，以及一些主体内容
     *
     * @return body
     */
    @Override
    public byte[] getBody() {
        if (getParams() != null && getParams().size() > 0 && !isUrlRequest()) {
            return HttpUtils.encodeParametersToBytes(getParams(), getParamsEncoding());
        }
        return null;
    }

    /**
     * @return the params body request
     */
    public Map<String, String> getParams() {
        if (isUrlRequest()) {
            return Collections.emptyMap();
        }
        return requestParams;
    }

    /**
     * name=value
     * if array
     * name[0]=value
     * name[1]=value
     *
     * @param key
     * @param value
     */
    public void addParam(String key, String value) {
        this.requestParams.put(key, value);
    }

    /**
     * name[0]=value1
     * name[1]=value2
     *
     * @param key
     * @param values
     */
    public void addParam(String key, String... values) {
        for (int i = 0; i < values.length; i++) {
            this.requestParams.put(key + "[" + i + "]", values[i]);
        }
    }

    public void addParamInt(String key, Integer[] values) {
        this.requestParamsInt.put(key, values);
    }

    /**
     * @return the url with params
     */
    public String getUrl() {
        if (isUrlRequest()) {
            String buildUrl;
            if (url.contains("?")) {
                buildUrl = url + "&";
            } else {
                buildUrl = url + "?";
            }

            StringBuilder extra = null;
            if (!requestParamsInt.isEmpty()) {
                extra = new StringBuilder();
                extra.append("&");
                for (String key : requestParamsInt.keySet()) {
                    Integer[] data = requestParamsInt.get(key);
                    for (int i : data) {
                        extra.append(key).append("[]=").append(i).append("&");
                    }
                }
            }
            if (extra != null) {
                return buildUrl + HttpUtils.encodeParameters(requestParams, getParamsEncoding()) + extra.toString();
            } else {
                return buildUrl + HttpUtils.encodeParameters(requestParams, getParamsEncoding());
            }
        }

        return url;
    }

    private boolean isUrlRequest() {
        if (this.requestParams.size() > 0
            && (getMethod() == HttpMethod.GET || getMethod() == HttpMethod.HEAD || getMethod() == HttpMethod.DELETE)) {
            return true;
        }
        return false;
    }

}
