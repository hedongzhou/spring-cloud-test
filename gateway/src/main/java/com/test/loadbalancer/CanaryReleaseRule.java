package com.test.loadbalancer;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.ZoneAvoidanceRule;

/**
 * 金丝雀发布规则
 *
 * @author hedongzhou
 * @since 2019/10/24
 */
public class CanaryReleaseRule extends ZoneAvoidanceRule {

    /**
     * 检查类型
     */
    private String checkType;

    public CanaryReleaseRule() {
    }

    public CanaryReleaseRule(String checkType) {
        this.checkType = checkType;
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return new CanaryServerPredicate(checkType);
    }

}
