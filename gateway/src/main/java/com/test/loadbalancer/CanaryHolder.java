package com.test.loadbalancer;

/**
 * 金丝雀名称
 *
 * @author hedongzhou
 * @since 2019/10/24
 */
public class CanaryHolder {

    private static final ThreadLocal<String> CANARY = new InheritableThreadLocal<>();

    public static String get() {
        return CANARY.get();
    }

    public static void set(String canary) {
        CANARY.set(canary);
    }

    public static void clear() {
        CANARY.remove();
    }

}
