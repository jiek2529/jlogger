package com.jiek.jlogger;

/**
 * 日志器
 * 提供 JLog.v JLog.d JLog.i JLog.v Jlog.e
 * 使用方便：只需引入此库，总将 Android 默认日志器， 改为 JLog，其他使用方式完全一致
 * 配置灵活：Logger 日志级别控制
 * 标签管理：注册制日志标签，未注册的日志不被输出
 * 性能优越：由总开关与标签开关共同控制，只处理被注册的日志
 */
public final class JLog {

    public static void v(String tag, String msg) {
        println(android.util.Log.VERBOSE, tag, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        println(android.util.Log.VERBOSE, tag, msg, tr);
    }

    public static void d(String tag, String msg) {
        println(android.util.Log.DEBUG, tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        println(android.util.Log.DEBUG, tag, msg, tr);
    }

    public static void i(String tag, String msg) {
        println(android.util.Log.INFO, tag, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        println(android.util.Log.INFO, tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        println(android.util.Log.WARN, tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        println(android.util.Log.WARN, tag, msg, tr);
    }

    public static void w(String tag, Throwable tr) {
        println(android.util.Log.WARN, tag, tr);
    }

    public static void e(String tag, String msg) {
        println(android.util.Log.ERROR, tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        println(android.util.Log.ERROR, tag, msg, tr);
    }

    public static int println(int priority, String tag, String msg) {
        if (LogTag.getInstance().hasTag(tag)) {
            return android.util.Log.println(priority, tag, msg);
        }
        return -1;
    }

    public static int println(int priority, String tag, String msg, Throwable tr) {
        if (Logger.isLogging(priority) && LogTag.getInstance().hasTag(tag)) {
            return android.util.Log.println(priority, tag, msg + "\n" + android.util.Log.getStackTraceString(tr));
        }
        return -1;
    }

    public static int println(int priority, String tag, Throwable tr) {
        if (Logger.isLogging(priority) && LogTag.getInstance().hasTag(tag)) {
            return android.util.Log.println(priority, tag, android.util.Log.getStackTraceString(tr));
        }
        return -1;
    }


}
