package com.test.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 金丝雀发布配置
 *
 * @author hedongzhou
 * @since 2019/10/25
 */
@Data
@Component
@ConfigurationProperties(prefix = "zuul.ribbon.canary-release")
public class CanaryReleaseProperties {

    /**
     * 是否启用
     */
    private boolean enabled;

    /**
     * 检查类型
     */
    private String checkType = "ip";

}
