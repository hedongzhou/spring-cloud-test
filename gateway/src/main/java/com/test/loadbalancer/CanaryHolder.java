package com.test.loadbalancer;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 金丝雀名称
 *
 * @author hedongzhou
 * @since 2019/10/24
 */
public class CanaryHolder {

    private static final ThreadLocal<Map<String, String>> CANARY = new InheritableThreadLocal<>();

    /**
     * 是否为空
     *
     * @return
     */
    public static boolean isEmpty() {
        Map<String, String> canaryMap = CANARY.get();
        return canaryMap == null || canaryMap.isEmpty();
    }

    /**
     * 是否包含服务
     *
     * @param serviceName
     * @return
     */
    public static boolean contains(String serviceName) {
        if (isEmpty()) {
            return false;
        }

        return CANARY.get().containsKey(serviceName);
    }

    /**
     * 获取目标
     *
     * @param serviceName
     * @return
     */
    public static String get(String serviceName) {
        return isEmpty() ? null : CANARY.get().get(serviceName);
    }

    /**
     * 设置信息
     *
     * @param canary
     */
    public static void set(String canary) {
        clear();

        canary = StringUtils.trim(canary);
        if (StringUtils.isNotBlank(canary)) {
            Map<String, String> canaryMap = Arrays.stream(StringUtils.split(canary, ";"))
                    .map(str -> StringUtils.split(str, ","))
                    .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1], (a, b) -> a));
            CANARY.set(canaryMap);
        }
    }

    /**
     * 清理
     */
    public static void clear() {
        CANARY.remove();
    }

}
