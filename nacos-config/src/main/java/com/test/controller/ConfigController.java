package com.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置
 *
 * @author hedongzhou
 * @since 2019/10/06
 */
@RefreshScope
@RestController
public class ConfigController {

    @Value("${useLocalCache}")
    private boolean useLocalCache;

    @Value("${name}")
    private String name;

    /**
     * 获取配置：是否使用本地缓存
     *
     * @return
     */
    @RequestMapping("/config/getUseLocalCache")
    public boolean getUseLocalCache() {
        return this.useLocalCache;
    }

    /**
     * 获取配置：名称
     *
     * @return
     */
    @RequestMapping("/config/getName")
    public String getName() {
        return this.name;
    }

}
