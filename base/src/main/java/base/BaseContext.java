package base;

import android.content.Context;

import base.core.cache.CacheManager;
import base.core.http.HttpFactory;
import base.core.http.HttpUtils;
import base.core.image.GImageLoader;

/**
 * Created by zhaotao on 16/8/1.
 */
public class BaseContext {

    private static Context sContext;

    public static void InitLibrary(Context context) {
        sContext = context;

        HttpFactory.register(context);
        CacheManager.register(context);
        HttpUtils.initSSL();
        GImageLoader.getInstance().init(sContext);
    }

    public static Context getContext() {
        return sContext;
    }

}
