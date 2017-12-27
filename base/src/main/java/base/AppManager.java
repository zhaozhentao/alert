package base;

import android.app.Activity;
import android.util.Log;

import java.util.Iterator;
import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author ZhiHui_Chen
 * @Email: 6208317#qq.com
 * @Date 2013-5-14
 */
public class AppManager {
    private static final String TAG = AppManager.class.getName();

    /**
     * Activity记录栈
     */
    private static Stack<Activity> activityStack = new Stack<Activity>();
    /**
     * AppManager单例
     */
    private static AppManager instance;

    /**
     * 单例
     */
    private AppManager() {
    }

    /**
     * 获取AppManager单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (activityStack.size() > 0) {
            return activityStack.get(activityStack.size() - 1);
        } else {
            return null;
        }
    }


    /**
     * 结束指定类名的Activity
     */
    public <T> T getActivity(Class<T> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return (T) activity;
            }
        }
        return null;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        Activity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束非当前的所有Activity
     */
    public void finishOtherAll(Class<?> cls) {
        Stack<Activity> remove = new Stack<>();
        for (Activity rm : activityStack) {
            if (!rm.getClass().equals(cls)) {
                remove.add(rm);
            }
        }

        activityStack.removeAll(remove);

        if (remove.size() == 0) {
            return;
        }

        for (Activity finish : remove) {
            finish.finish();
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            Log.e(TAG, "退出应用失败", e);
        } finally {
            // android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}
