package base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.webkit.WebView;

/**
 * 网络工具类
 * <p/>
 * Created by zhihui_chen on 14-8-19.
 */
public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getName();

    /**
     * WIFI网络
     */
    public static final int NETTYPE_WIFI = 0x01;
    /**
     * CMWAP网络
     */
    public static final int NETTYPE_CMWAP = 0x02;
    /**
     * CMNET网络
     */
    public static final int NETTYPE_CMNET = 0x03;

    /**
     * 检测网络是否可用
     *
     * @return isConnected
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    public static boolean isWifiAvailable(Context context) {
        if (getNetworkType(context) == NETTYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static void callJavaScriptFunction(WebView webView, String methodName, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:try{");
        stringBuilder.append(methodName);
        stringBuilder.append("(");
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                Object param = params[i];
                if (param instanceof String) {
                    stringBuilder.append("'");
                    stringBuilder.append(param);
                    stringBuilder.append("'");
                }
                if (i < params.length - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        stringBuilder.append(")}catch(error){console.error(error.message)}");
        webView.loadUrl(stringBuilder.toString());
    }
}
