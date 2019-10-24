package com.test.route;

import com.alibaba.nacos.api.annotation.NacosProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.ArrayList;
import java.util.List;

/**
 * Nacos动态路由
 *
 * @author hedongzhou
 * @since 2019/10/16
 */
//@Component
public class NacosDynamicRoute implements ApplicationEventPublisherAware {

    private static final List<String> ROUTE_LIST = new ArrayList<>();

    @Autowired
    private NacosProperties nacosProperties;

//    @Autowired
//    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

//    @PostConstruct
    public void dynamicRouteByNacosListener() {
//        try {
//            ConfigService configService = NacosFactory.createConfigService(nacosProperties.getServerAddr());
//            configService.getConfig(nacosProperties.getPrefix(), group, 5000);
//            configService.addListener(dataId, group, new Listener() {
//                @Override
//                public void receiveConfigInfo(String configInfo) {
//                    clearRoute();
//                    try {
//                        List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(configInfo, RouteDefinition.class);
//                        for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
//                            addRoute(routeDefinition);
//                        }
//                        publish();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public Executor getExecutor() {
//                    return null;
//                }
//            });
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }
    }

//    private void clearRoute() {
//        for (String id : ROUTE_LIST) {
//            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
//        }
//        ROUTE_LIST.clear();
//    }
//
//    private void addRoute(RouteDefinition definition) {
//        try {
//            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//            ROUTE_LIST.add(definition.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void publish() {
//        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
//    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
