package com.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Nacos配置
 *
 * @author hedongzhou
 * @since 2019/10/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.cloud.nacos.config")
public class NacosProperties {

    /**
     * 应用前缀
     */
    private String prefix;

    /**
     * Nacos服务地址
     */
    private String serverAddr;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 组别
     */
    private String group;

}
