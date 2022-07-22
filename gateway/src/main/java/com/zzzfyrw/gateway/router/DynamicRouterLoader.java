package com.zzzfyrw.gateway.router;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.zzzfyrw.common.json.fastjson.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

@Slf4j
@Component
@DependsOn({"gatewayRouterConfig"})
public class DynamicRouterLoader implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;
    private ConfigService configService;
    @Autowired
    private GatewayRouterConfig routerConfig;
    @Autowired
    private DynamicRouterService dynamicRouterService;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @PostConstruct
    public void init() {
        String dataType = routerConfig.TYPE;
        log.info("init router");
        switch (dataType.toLowerCase()){
            case GatewayRouterConfig.NACOS:
                loadByNacos();
                break;
            case GatewayRouterConfig.MYSQL:
                loadByRedis();
                break;
            default:
                log.info("err load router type");
                break;
        }
        log.info("load router finished");
    }

    /**
     * 获取nacos路由数据
     */
    private void loadByNacos(){
        configService = createConfigService();
        try {
            assert configService != null;
            String routerStr = configService.getConfig(routerConfig.DATA_ID,
                    routerConfig.ROUTE_GROUP,
                            5000);
            if(StringUtils.isEmpty(routerStr)){
                log.info("加载的路由数据为空: {}",routerStr);
                return ;
            }
            log.info("路由配置:\r\n{}", routerStr);
            List<RouteDefinition> routes = FastJsonUtil.jsonToList(routerStr,RouteDefinition.class);
            for (RouteDefinition definition : routes) {
                log.info("update route : {}", definition.toString());
                dynamicRouterService.add(definition);
            }
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            nacosListener(routerConfig.DATA_ID, routerConfig.ROUTE_GROUP);
        } catch (NacosException e) {
            log.error("无法获取网关路由数据", e);
        }

    }

    /**
     * 获取redis路由数据
     */
    private void loadByRedis(){



    }

    /**
     * 创建ConfigService
     *
     * @return
     */
    private ConfigService createConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", routerConfig.SERVER_ADDR);
            properties.setProperty("namespace", routerConfig.NAMESPACE);
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("创建ConfigService异常", e);
        }
        return null;
    }

    /**
     * 监听Nacos下发的动态路由配置
     *
     * @param dataId
     * @param group
     */
    public void nacosListener(String dataId, String group) {
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新:\n\r{}", configInfo);
                    List<RouteDefinition> definitionList = FastJsonUtil.jsonToList(configInfo, RouteDefinition.class);
                    for (RouteDefinition definition : definitionList) {
                        dynamicRouterService.update(definition);
                    }
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }


}
