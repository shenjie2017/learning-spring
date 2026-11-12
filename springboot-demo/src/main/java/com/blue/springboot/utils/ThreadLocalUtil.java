package com.blue.springboot.utils;

import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String,Object>> tl = new ThreadLocal<>();

    public static void set(Map<String,Object> o) {
        tl.set(o);
    }
    public static Map<String,Object> get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
