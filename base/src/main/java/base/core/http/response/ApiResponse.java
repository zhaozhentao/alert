package base.core.http.response;

import android.util.Log;


import base.utils.GsonUtils;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 接口数据
 * <p/>
 * Created by tony on 8/24/14.
 */
public class ApiResponse implements Serializable {

    private int code;
    private String response;

    public ApiResponse() {
    }

    public ApiResponse(int code, String response) {
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", response='" + response + '\'' +
                '}';
    }

    /**
     * 通过response转换实体
     *
     * @param type
     * @param <V>
     * @return
     */
    public <V> V convert(Class<V> type) {
        try {
            return GsonUtils.fromJson(response, type);
        } catch (Exception e) {
            Log.e("ApiResponse", "convert error!!!", e);
        }
        return null;
    }

    /**
     * 通过response转换实体
     *
     * @param type
     * @param <V>
     * @return
     */
    public <V> V convert(Type type) {
        try {
            return GsonUtils.fromJson(response, type);
        } catch (Exception e) {
            Log.e("ApiResponse", "convert error!!!", e);
        }
        return null;
    }
}
