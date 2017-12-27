package base.core.http.impl;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import base.core.event.OnEventListener;
import base.core.http.HttpConstants;
import base.core.http.api.AbstractHttpService;
import base.core.http.api.HttpListener;
import base.core.http.request.GZipRequest;
import base.core.http.request.HttpMethod;
import base.core.http.request.Request;
import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;
import base.utils.NetworkUtils;
import base.core.http.HttpConstants.Error;

import java.util.Map;

/**
 * Volley　实现类
 * <p/>
 * 只对GET请求进行缓存
 * <p/>
 * Created by zhihui_chen on 14-8-4.
 */
public class VolleyImpl extends AbstractHttpService {
    private static final String TAG = VolleyImpl.class.getName();

    private Context context;
    private RequestQueue mRequestQueue;

    public VolleyImpl(Context context) {
        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 发送一个请求
     *
     * @param request
     * @param httpListener
     */
    @Override
    public void sendRequest(final Request request, final HttpListener<?> httpListener) {

        // start
        if (httpListener != null) {
            httpListener.onStart();
        } else {
            if (HttpConstants.DEBUG) Log.w(TAG, "httpListener is null!!!");
        }

        // 网络不可用
        if (!NetworkUtils.isNetworkConnected(context)) {
            if (httpListener == null) {
                return;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpError httpError = new HttpError();
                        httpError.setCode(Error.NETWORK_ERROR.getCode());
                        httpError.setMessage(Error.NETWORK_ERROR.getMessage());
                        httpListener.onFailure(httpError);
                    } finally {
                        httpListener.onFinish();
                    }
                }
            }, 1000);
            return;
        }

        doStringRequest(request, httpListener);
    }

    /**
     * String 内容请求
     *
     * @param request
     * @param httpListener
     */
    public void doStringRequest(final Request request, final HttpListener<?> httpListener) {
        final HttpMethod method = request.getMethod();

        // 如果为GET方法，查检本地缓存
        if (method == HttpMethod.GET && request.getCacheTime() != 0) {
            // 是否已经有缓存了，存在自动返回缓存数据
            if (fireCache(request.getUrl(), httpListener)) return;
        }

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (HttpConstants.DEBUG) Log.d(TAG, response);

                if (httpListener == null) return;

                try {
                    httpListener.handleResponse(new ApiResponse(0, response));
                } finally {
                    httpListener.onFinish();
                }

            }
        };
        // 添加全局 headers
        request.getHeaders().putAll(getHeaderMap());

        final GZipRequest stringRequest = new GZipRequest(method.getId()
            , request.getUrl().replace("&&", "&").replace("?&", "&")
            , responseListener
            , createErrorListener(httpListener)) {

            /**
             * HEAD 参数
             * @return
             * @throws AuthFailureError
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return request.getHeaders();
            }

            @Override
            public String getBodyContentType() {
                return request.getBodyContentType();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return request.getBody();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Map<String, String> headers = response.headers;
                return super.parseNetworkResponse(response);
            }
        };
        if (HttpConstants.DEBUG) {
            Log.d(TAG, "request [" + stringRequest.getMethod() + "]: " + stringRequest.getUrl());
        }
        stringRequest.setShouldCache(false);
        if (method == HttpMethod.POST) {
            Log.e("post", "post");
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, 0, 2f));
        } else {
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(HttpConstants.REQUEST_TIMEOUT_MS, HttpConstants.REQUEST_MAX_RETRIES, 2f));
        }
        stringRequest.setTag(TAG);
        mRequestQueue.add(stringRequest);
        request.setCancelListener(new OnEventListener<Boolean>() {
            @Override
            public void onEvent(Boolean event) {
                if (event) {
                    stringRequest.cancel();
                }
            }
        });
    }

    /**
     * 创建错误监听器
     *
     * @param httpListener
     * @return
     */
    private Response.ErrorListener createErrorListener(final HttpListener<?> httpListener) {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "VolleyError", error);
                if (httpListener == null) return;

                HttpError httpError = makeError(error);
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    // 错误内容
                    int statusCode = error.networkResponse.statusCode;
                    String response = new String(error.networkResponse.data);
                    // 转换错误信息
                    httpError = new HttpError(statusCode, response);
                    Log.e(TAG, statusCode + " HttpError: " + response);
                }
                try {
                    httpListener.handleError(httpError);
                } finally {
                    httpListener.onFinish();
                }
            }

        };
        return errorListener;
    }

    /**
     * 产生一个错误信息
     *
     * @param error
     * @return
     */
    private HttpError makeError(VolleyError error) {
        HttpError httpError = new HttpError();
        if (error instanceof TimeoutError) {
            httpError.setCode(Error.TIMEOUT_ERROR.getCode());
            httpError.setMessage(Error.TIMEOUT_ERROR.getMessage());
        } else if (error instanceof NoConnectionError) {
            httpError.setCode(Error.NO_CONNECTION_ERROR.getCode());
//            httpError.setMessage(error.getMessage());
            httpError.setMessage(Error.NO_CONNECTION_ERROR.getMessage());
        } else if (error instanceof AuthFailureError) {
            httpError.setCode(Error.AUTH_FAILURE_ERROR.getCode());
            httpError.setMessage(Error.AUTH_FAILURE_ERROR.getMessage());
        } else if (error instanceof ServerError) {
            httpError.setCode(Error.SERVER_ERROR.getCode());
            httpError.setMessage(Error.SERVER_ERROR.getMessage());
        } else if (error instanceof NetworkError) {
            httpError.setCode(Error.NETWORK_ERROR.getCode());
            httpError.setMessage(Error.NETWORK_ERROR.getMessage());
        } else if (error instanceof ParseError) {
            httpError.setCode(Error.PARSE_ERROR.getCode());
            httpError.setMessage(Error.PARSE_ERROR.getMessage());
        } else {
            httpError.setCode(Error.DEFUALT_ERROR.getCode());
            httpError.setMessage(Error.DEFUALT_ERROR.getMessage());
        }
        return httpError;
    }
}
