package base.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by Wilson on 14-8-21.
 */
public class StringUtils {
    private static final String TAG = StringUtils.class.getName();

    public static final String VERSION_SEPERATOR = ".";
    private final static Pattern emailer = Pattern
        .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern mobiler = Pattern
        .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

    public final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        }
    };
    public final static ThreadLocal<SimpleDateFormat> dateFormater7 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        }
    };
    public final static ThreadLocal<SimpleDateFormat> dateFormater_cn_detail = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        }
    };
    public final static ThreadLocal<SimpleDateFormat> dateFormater_cn_date = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater_time = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater_simle_time = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    public final static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        }
    };
    public final static ThreadLocal<SimpleDateFormat> dateFormater5 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    public final static ThreadLocal<SimpleDateFormat> dateFormater8 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    public final static ThreadLocal<SimpleDateFormat> dateFormater9 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public final static ThreadLocal<SimpleDateFormat> dateFormater6 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        }
    };

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            Date date;
            if (sdate.length() > 10) {
                date = dateFormater4.get().parse(sdate);
            } else {
                date = dateFormater2.get().parse(sdate);
            }
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDateTime(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDateTimeWithZone(String sdate) {
        try {
            return dateFormater3.get().parse(sdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDateTime2(String sdate) {
        try {
            return dateFormater4.get().parse(sdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        return dateFormater2.get().format(date);
    }

    public static String dateFormat2(Date date) {
        return dateFormater8.get().format(date);
    }

    public static Date toDate3(String date) {
        try {
            return dateFormater8.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date toDate4(String date) {
        try {
            return dateFormater9.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateFormat(String date) {
        if (isNotEmpty(date)) {
            return dateFormater2.get().format(toDate(date));
        }
        return "";
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateTimeFormat(Date date) {
        return dateFormater.get().format(date);
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateTimeFormat(String date) {
        return dateFormater.get().format(toDate(date));
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateMinuteFormat(String date) {
        return dateFormater5.get().format(toDate(date));
    }

    public static String fromTimeStampToString(long timeStamp) {
        return dateFormater5.get().format(new Date(timeStamp));
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim());
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    public static boolean isPswdValid(String pswd) {
        if (isNotEmpty(pswd) && pswd.trim().length() >= 6) {
            return true;
        }
        return false;
    }

    /**
     * 判断是不是一个合法手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (mobile == null || mobile.trim().length() == 0)
            return false;

//        return mobiler.matcher(mobile).matches();

        return mobile.trim().length() == 11;
    }

    public static boolean notEquals(String src, String target) {
        return !equals(src, target);
    }

    public static boolean equals(String src, String target) {
        if (isEmpty(src) || isEmpty(target))
            return false;
        return src.equals(target);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0.0;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static float toFloat(String obj) {
        try {
            return Float.parseFloat(obj);
        } catch (Exception e) {
        }
        return 0.0f;
    }

    /**
     * 如果含小数位 输出小数位，否则去掉小数位
     *
     * @param price
     * @return
     */
    public static String getPrettyPrice(float price) {
        int intPrice = (int) price;
        if ((price - intPrice) == 0f) {
            return String.valueOf(intPrice);
        }
        return String.valueOf(price);
    }

    public static List<String> stringToList(String str, String seperator) {
        List<String> itemList = new ArrayList<String>();
        if (isEmpty(str)) {
            return itemList;
        }
        StringTokenizer st = new StringTokenizer(str, seperator);
        while (st.hasMoreTokens()) {
            itemList.add(st.nextToken());
        }

        return itemList;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param time
     * @return
     */
    public static String friendlyTime(String time) {
        String fTime = "";
        long week = 7 * 24 * 60 * 60 * 1000;

        Date date = null;

        if (time == null) return fTime;

        try {
            date = dateFormater3.get().parse(time);
        } catch (Exception e) {
        }
        long onWeek = System.currentTimeMillis() - date.getTime();
        if (onWeek > 0 && onWeek <= week) {
            fTime += "本周" + date.getDay() + "" + date.getHours() + ":" + date.getMinutes();
        } else {
            return dateTimeFormat(date);
        }
        return fTime;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param lTime
     * @return
     */
    public static String friendlyTime(long lTime) {
        Date time = null;
        if (lTime == 0) {
            time = new Date();
        } else {
            time = new Date(lTime);
        }
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                    (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                    + "分钟前更新";
            else
                ftime = hour + "小时前更新";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                    (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                    + "分钟前更新";
            else
                ftime = hour + "小时前更新";
        } else if (days == 1) {
            ftime = "昨天更新";
        } else if (days == 2) {
            ftime = "前天更新";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前更新";
        } else if (days > 10) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * 以一种简单的方式格式化字符串
     * 如  String s = StringHelper.format("{0} is {1}", "apple", "fruit");
     * System.out.println(s);	//输出  apple is fruit.
     *
     * @param pattern
     * @param args
     * @return
     */
    public static String format(String pattern, Object... args) {
        for (int i = 0; i < args.length; i++) {
            pattern = pattern.replace("{" + i + "}", args[i].toString());
        }
        return pattern;
    }

    /**
     * 当前时间是否早于date1
     *
     * @param date1
     * @return
     */
    public static boolean isEarlier(String date1) {
        if (StringUtils.isEmpty(date1)) {
            return true;
        }
        try {
            Date time1 = StringUtils.toDateTimeWithZone(date1);
            Date time2 = new Date();
            if (time2.before(time1)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * date1是否早于date2
     *
     * @param date1
     * @return
     */
    public static boolean isEarlier(String date1, String date2) {
        if (StringUtils.isEmpty(date1)) {
            return true;
        }
        date1 = date1.replace("T", " ");
        date2 = date2.replace("T", " ");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date time1 = df.parse(date1);
            Date time2 = df.parse(date2);
            if (time1.before(time2)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * "yyyy-MM-dd'T'HH:mm:ssZ" to  "yyyy年MM月dd日''HH:mm:ss"
     *
     * @param dateTimeStr
     * @return
     */
    public static String getCnDateTimeFromTZ(String dateTimeStr) {
        Date date;
        try {
            date = dateFormater3.get().parse(dateTimeStr);
        } catch (Exception e) {
            return "";
        }

        return dateFormater_cn_detail.get().format(date);
    }

    /**
     * "yyyy-MM-dd'T'HH:mm:ssZ" to  "yyyy年MM月dd日''HH:mm:ss"
     *
     * @param dateTimeStr
     * @return
     */
    public static String getDateTimeFromTZ(String dateTimeStr) {
        Date date;
        try {
            date = dateFormater3.get().parse(dateTimeStr);
        } catch (Exception e) {
            return "";
        }

        return dateFormater7.get().format(date);
    }

    /**
     * "yyyy-MM-dd'T'HH:mm:ssZ" to  "yyyy年MM月dd日''HH:mm:ss"
     *
     * @param dateTimeStr
     * @return
     */
    public static String getCnDateTimeFromTZ2(String dateTimeStr) {
        Date date;
        try {
            date = dateFormater4.get().parse(dateTimeStr);
        } catch (Exception e) {
            return "";
        }

        return dateFormater_cn_detail.get().format(date);
    }

    /**
     * "yyyy-MM-dd'T'HH:mm:ssZ" to  "HH:mm:ss"
     *
     * @param dateTimeStr
     * @return
     */
    public static String getTimeFromTZ(String dateTimeStr) {
        Date date;
        try {
            date = dateFormater3.get().parse(dateTimeStr);
        } catch (Exception e) {
            return "";
        }

        return dateFormater_simle_time.get().format(date);
    }

    /**
     * "yyyy-MM-dd'T'HH:mm:ssZ" to  "HH:mm:ss"
     *
     * @param dateTimeStr
     * @return
     */
    public static String getSimpleTimeFromTZ(String dateTimeStr) {
        Date date;
        try {
            date = dateFormater3.get().parse(dateTimeStr);
        } catch (Exception e) {
            return "";
        }

        return dateFormater_simle_time.get().format(date);
    }

    /**
     * double format
     *
     * @param number
     * @return
     */
    public static String formatDouble(Double number) {
        DecimalFormat r = new DecimalFormat("0.00");
        return r.format(number);
    }

    /**
     * UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 在对话中是否显示时间
     *
     * @param dateStr1 上一条
     * @param dateStr2 这一条
     * @return
     */
    public static boolean isShowMessageTime(String dateStr1, String dateStr2) {
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
        SimpleDateFormat sdfHour = new SimpleDateFormat("HH");
        SimpleDateFormat sdfMin = new SimpleDateFormat("mm");

        Date now = toDateTimeWithZone(dateStr1);
        Date other = toDateTimeWithZone(dateStr2);
        int tempDay = Integer.parseInt(sdfDay.format(now))
            - Integer.parseInt(sdfDay.format(other));
        int tempHour = Integer.parseInt(sdfHour.format(now))
            - Integer.parseInt(sdfHour.format(other));
        int tempMin = Integer.parseInt(sdfMin.format(now))
            - Integer.parseInt(sdfMin.format(other));
        Log.d("isShowMessageTime", dateStr1 + "----" + dateStr2);
        Log.d("isShowMessageTime", tempDay + "||" + tempHour + "||" + tempMin);


        if (tempDay == 0 && tempHour == 0 && Math.abs(tempMin) <= 10) {
            Log.d("isShowMessageTime", "not show");

            return false;
        }
        Log.d("isShowMessageTime", "show");

        return true;
    }

    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(time));
    }

    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(new Date(time));
    }

    public static String getTime2(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(time));
    }

    public static String getTime3(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(time));
    }

    public static String getTime4(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        int valueLength = 0;
        if (value == null) return valueLength;

        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static String appendString(Object... strings) {
        StringBuilder sb = new StringBuilder();
        for (Object string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = minute + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + minute + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String format2(int i) {
        return String.format("%02d", i);
    }

    public static Date toDate2(String yyyyMMddhhmm) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date date = simpleDateFormat.parse(yyyyMMddhhmm);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatCn(Date date) {
        return dateFormater_cn_date.get().format(date);
    }

    public static Date getDateFromCNString(String cnString) {
        try {
            return dateFormater_cn_date.get().parse(cnString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
