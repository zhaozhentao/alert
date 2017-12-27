package base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommUtils {

    /**
     * @return
     * @Description get series number
     * @date Jun 27, 2015 10:13:14 AM
     */
    public static String getSN(Context context) {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (serial != null) {
            return serial;
        } else {
            return "-1";
        }
    }

    /**
     * @return
     * @Description device id
     * @date Jun 27, 2015 10:13:31 AM
     */
    public static String getDeviceId(Context context) {
        TelephonyManager telephonemanage = (TelephonyManager) context
            .getSystemService(Context.TELEPHONY_SERVICE);
        String id = telephonemanage.getDeviceId();
        if (id != null) {
            return id;
        } else {
            return "-1";
        }
    }

    /**
     * @return
     * @Description 时间戳转日期, yyyy-MM-dd
     * @date Jul 8, 2015 9:44:46 PM
     */
    public static String timeStamp2Date(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(date));
    }

    /**
     * @return
     * @Description 时间戳转日期, yyyy-MM-dd hh:mm:ss
     * @date Jul 8, 2015 9:44:46 PM
     */
    public static String timeStamp2Simple(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(date));
    }

    /**
     * @return
     * @Description 时间戳转时间
     * @date Jul 8, 2015 9:44:46 PM
     */
    public static String timeStamp2Time(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date(date));
    }

    /**
     * @return
     * @Description 时间戳转date
     * @date Jul 8, 2015 9:44:46 PM
     */
    public static Date timeStamp2DateTime(long date) {
        return new Date(date);
    }

    /**
     * @return
     * @Description 計算停車時長
     * @date Jul 8, 2015 9:51:55 PM
     */
    public static String timeLong(long startTime, long endTime) {
        Date startDate = new Date(startTime);
        Date endDate = null;
        StringBuffer buffer = null;
        long day = 0;
        long hours = 0;
        long mins = 0;
        long second = 0;
        if (startTime == 0) {
            return "0";
        }
        if (endTime == 0) {
            endDate = new Date();
            second = getTimeDiffer(endDate, startDate);
        } else {
            endDate = new Date(endTime);
            second = getTimeDiffer(endDate, startDate);
        }
        buffer = new StringBuffer();
        day = second / 60 / 60 / 24;
        hours = second / 3600 % 24;
        mins = second / 60 % 60;
        if (day > 0) {
            buffer.append(day).append("天");
        }
        if (hours > 0) {
            buffer.append(hours).append("小时");
        }
        if (mins > 0) {
            buffer.append(mins).append("分钟");
        }
        return buffer.toString();
    }

    /**
     * @Description 秒 转换成 HH小时mm分钟ss秒
     */
    public static String secondToTime(long time) {
        String html = "0秒";
        if (time != 0) {
            String format;
            Object[] array;
            Integer hours = (int) (time / (60 * 60));
            Integer minutes = (int) (time / (60) - hours * 60);
            Integer second = (int) (time - minutes * 60 - hours * 60 * 60);
            if (hours > 0) {
                format = "%1$,d小时%2$,d分钟%3$,d秒";
                array = new Object[]{hours, minutes, second};
            } else if (minutes > 0) {
                format = "%1$,d分钟%2$,d秒";
                array = new Object[]{minutes, second};
            } else {
                format = "%1$,d秒";
                array = new Object[]{second};
            }
            html = String.format(format, array);
        }
        return html;
    }

    /**
     * @author:10957@ake.com.cn
     * @Description:计算时间差 (秒)
     * @return:
     * @date:2015-2-10
     */
    public static long getTimeDiffer(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long seconds = diff / (1000);
        return seconds;
    }

    /**
     * @return
     * @Description version code
     * @date Jul 23, 2015 2:31:03 PM
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author:10957@ake.com.cn
     * @Description:获取屏幕分辨率
     * @return:
     * @date:2015-2-9
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
            .getMetrics(dm);
        return dm.widthPixels;
    }

    public static boolean isLeapYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
