package com.test.loadbalancer;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 金丝雀服务选择
 *
 * @author hedongzhou
 * @since 2019/10/24
 */
@Slf4j
public class CanaryServerPredicate extends AbstractServerPredicate {

    /**
     * 名称Key
     */
    private static final String CANARY_NAME_KEY = "canaryName";

    /**
     * 检查类型
     */
    private String checkType;

    public CanaryServerPredicate(String checkType) {
        this.checkType = checkType;
    }

    @Override
    public List<Server> getEligibleServers(List<Server> servers, Object loadBalancerKey) {
        try {
            return super.getEligibleServers(servers, loadBalancerKey);
        } finally {
            CanaryHolder.clear();
        }
    }

    @Override
    public boolean apply(PredicateKey predicateKey) {
        if (CanaryHolder.isEmpty()) {
            return true;
        }

        NacosServer server = (NacosServer) predicateKey.getServer();
        String serviceName = server.getInstance().getServiceName();
        serviceName = serviceName.substring(serviceName.lastIndexOf("@") + 1);
        if (!CanaryHolder.contains(serviceName)) {
            return true;
        }

        boolean pass = check(server, CanaryHolder.get(serviceName));
        if (pass) {
            log.info("hit canary: {}", server);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查
     *
     * @param server
     * @param target
     * @return
     */
    private boolean check(NacosServer server,
                          String target) {
        String compare;
        switch (this.checkType) {
            case "name":
                compare = server.getInstance().getMetadata().get(CANARY_NAME_KEY);
                break;
            case "ip":
            default:
                compare = server.getInstance().getIp();
                if (target.contains(":")) {
                    compare += ":" + server.getInstance().getPort();
                }
        }
        return target.equals(compare);
    }

}
