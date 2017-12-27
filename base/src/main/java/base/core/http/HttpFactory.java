package base.core.http;


import android.content.Context;

import base.core.http.api.HttpService;
import base.core.http.impl.VolleyImpl;

/**
 * 网络支持类
 * <p/>
 * Created by zhihui_chen on 14-8-4.
 */
public class HttpFactory {
    private static final String TAG = HttpFactory.class.getName();

    private static Context context = null;
    private static HttpService httpService = null;

    public static void register(Context context) {
        HttpFactory.context = context;
    }

    public static HttpService getHttpService() {
        if (httpService == null) {
            // 通过Volley进行实现
            httpService = new VolleyImpl(context);
        }
        return httpService;
    }

    public static HttpService newHttpService() {
        return new VolleyImpl(context);
    }

}
