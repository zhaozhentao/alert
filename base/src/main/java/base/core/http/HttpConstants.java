package base.core.http;

import android.content.res.Resources;

import base.BaseContext;
import base.R;

/**
 * Created by zhihui_chen on 14-8-14.
 */
public class HttpConstants {

    public static final boolean DEBUG = false;

    /**
     * 请求超时时间
     */
    public static final int REQUEST_TIMEOUT_MS = 16000;

    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 8000;

    /**
     * 读取超时时间
     */
    public static final int READ_TIMEOUT = 8000;

    /**
     * 请求重试次数
     */
    public static final int REQUEST_MAX_RETRIES = 0;

    /**
     * 参数默认编码
     */
    public static final String DEFAULT_PARAMS_ENCODING = "UTF-8";


    public final static String APPLICATION_OCTET_STREAM = "application/octet-stream";

    public final static String APPLICATION_JSON = "application/json";

    public static enum Error {

        DEFUALT_ERROR(0, "网络不给力，请检查网络"),

        /**
         * TimeoutError
         */
        TIMEOUT_ERROR(1, "网络不给力，请检查网络"),

        /**
         * NoConnectionError
         */
        NO_CONNECTION_ERROR(2, "网络异常，未连接成功"),

        /**
         * AuthFailureError
         */
        AUTH_FAILURE_ERROR(3, "登录信息无效，请重新登录"),

        /**
         * ServerError
         */
        SERVER_ERROR(4, "网络异常，服务器错误"),

        /**
         * NetworkError
         */
        NETWORK_ERROR(5, "网络不可用"),

        /**
         * ParseError
         */
        PARSE_ERROR(6, "数据解析错误"),

        /**
         * 请求数据为空
         */
        RESPONSE_NULL(100, "请求数据为空"),

        /**
         * 请求数据为空
         */
        UNKNOWN_ERROR(101, "应用未知错误");

        private int code;
        private String message;

        Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public static void updateErrorMessageLanguage(){
            Resources res = BaseContext.getContext().getResources();
            DEFUALT_ERROR.setMessage(res.getString(R.string.defualt_error));
            TIMEOUT_ERROR.setMessage(res.getString(R.string.timeout_error));
            NO_CONNECTION_ERROR.setMessage(res.getString(R.string.no_connecttion_error));
            AUTH_FAILURE_ERROR.setMessage(res.getString(R.string.auth_failure_error));
            SERVER_ERROR.setMessage(res.getString(R.string.server_error));
            NETWORK_ERROR.setMessage(res.getString(R.string.network_error));
            PARSE_ERROR.setMessage(res.getString((R.string.parse_error)));
            RESPONSE_NULL.setMessage(res.getString(R.string.response_null));
            UNKNOWN_ERROR.setMessage(res.getString(R.string.unknown_error));
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
