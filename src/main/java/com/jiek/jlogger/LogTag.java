package com.jiek.jlogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志标签管理
 */
public final class LogTag {
    //所有需要打印的日志标签管理
    private transient Map<String, Boolean> tags_enable;

    private static LogTag instance;

    private LogTag() {
        tags_enable = new ConcurrentHashMap<>();
    }

    protected static LogTag getInstance() {
        if (instance == null) {
            synchronized (LogTag.class) {
                if (instance == null) {
                    instance = new LogTag();
                }
            }
        }
        return instance;
    }

    /**
     * 日志管理添加 TAG
     *
     * @param tag
     */
    protected void register(String tag) {
        tags_enable.put(tag, true);
    }

    /**
     * 日志管理移除 TAG
     *
     * @param tag
     */
    protected void unregister(String tag) {
        tags_enable.remove(tag);
    }

    protected boolean hasTag(String tag) {
        Boolean aBoolean = tags_enable.get(tag);
        if (aBoolean == null) {
            tags_enable.put(tag, false);
            return false;
        } else {
            return aBoolean;
        }
    }
}
