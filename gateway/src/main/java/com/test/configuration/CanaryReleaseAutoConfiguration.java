package com.test.configuration;

import com.test.loadbalancer.CanaryReleaseRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 金丝雀发布
 *
 * @author hedongzhou
 * @since 2019/10/24
 */
@Configuration
@AutoConfigureBefore(RibbonClientConfiguration.class)
@ConditionalOnProperty(value = "zuul.ribbon.canary-release.enabled")
public class CanaryReleaseAutoConfiguration {

    @Autowired
    private CanaryReleaseProperties canaryReleaseProperties;

    @Bean
    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CanaryReleaseRule metadataAwareRule() {
        return new CanaryReleaseRule(canaryReleaseProperties.getCheckType());
    }

}
