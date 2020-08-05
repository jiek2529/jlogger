package com.jiek.jlogger;

import android.util.Log;

/**
 * 日志主管理（默认只开 ERROR）
 * 1. 日志级别开关
 * 2. 日志级别单独开关
 * 3. 日志存文件开关 【待实现】
 * 4. 日志配置文件外部加载 【待实现】
 * <p>
 * 日志开关由两部分组成
 * 1. 级别总开关
 * 2. LogTag 中注册了的日志才能被输出
 */
public final class Logger {
    private volatile static Logger instance;
    private static boolean isDebug = false;

    private static boolean LOG_VERBOSE_ENABLE = false;
    private static boolean LOG_DEBUG_ENABLE = false;
    private static boolean LOG_INFO_ENABLE = false;
    private static boolean LOG_WARN_ENABLE = false;
    private static boolean LOG_ERROR_ENABLE = true;
//    private static boolean LOG_ASSET_ENABLE = false;

    private static boolean SAVE_2_FILE = false;

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * 开启所有日志控制台输出
     */
    public static void setDebug() {
        isDebug = true;
        LOG_VERBOSE_ENABLE = true;
        LOG_DEBUG_ENABLE = true;
        LOG_INFO_ENABLE = true;
        LOG_WARN_ENABLE = true;
        LOG_ERROR_ENABLE = true;
    }

    /**
     * 单独对日志级别进行开关
     *
     * @param priority 日志级别值域： {@link android.util.Log.VERBOSE}
     *                 {@link android.util.Log.DEBUG}
     *                 {@link android.util.Log.INFO}
     *                 {@link android.util.Log.WARN}
     *                 {@link android.util.Log.ERROR}
     * @param enable
     */
    public static void enablePriority(int priority, boolean enable) {
        if (priority == Log.DEBUG) {
            LOG_DEBUG_ENABLE = enable;
        } else if (priority == Log.INFO) {
            LOG_INFO_ENABLE = enable;
        } else if (priority == Log.WARN) {
            LOG_WARN_ENABLE = enable;
        } else if (priority == Log.ERROR) {
            LOG_ERROR_ENABLE = enable;
        } else if (priority == Log.VERBOSE) {
            LOG_VERBOSE_ENABLE = enable;
        }
    }

    /**
     * 关闭所有日志控制台输出，ERROR 除外
     */
    public static void closeDebug() {
        LOG_VERBOSE_ENABLE = false;
        LOG_DEBUG_ENABLE = false;
        LOG_INFO_ENABLE = false;
        LOG_WARN_ENABLE = false;
        LOG_ERROR_ENABLE = true;
    }

    public static void registerTag(String tag) {
        LogTag.getInstance().register(tag);
    }

    protected static boolean isLogging(int priority) {
        if (priority == Log.DEBUG) {
            return LOG_DEBUG_ENABLE;
        } else if (priority == Log.INFO) {
            return LOG_INFO_ENABLE;
        } else if (priority == Log.WARN) {
            return LOG_WARN_ENABLE;
        } else if (priority == Log.ERROR) {
            return LOG_ERROR_ENABLE;
        } else if (priority == Log.VERBOSE) {
            return LOG_VERBOSE_ENABLE;
        } else {
            return false;
        }

    }

    /**
     * 日志存文件
     */
    public static void saveLog2ErrorFile() {
//        TODO 日志存文件，由配置文件定义
    }

    /**
     * 加载日志配置文件
     *
     * @param path
     */
    public static void initLogConfig(String path) {
        // TODO: 2020/8/5 待近需完成日志配置文件加载
    }
}
