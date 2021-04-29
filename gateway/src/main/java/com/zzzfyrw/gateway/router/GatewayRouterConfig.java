package com.zzzfyrw.gateway.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouterConfig {

    @Value("${gateway.router.data-id:gateway-router.json}")
    public String DATA_ID;
    @Value("${gateway.router.group:DEFAULT_GROUP}")
    public String ROUTE_GROUP;
    @Value("${gateway.route.type:nacos}")
    public String TYPE;
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public String SERVER_ADDR;
    @Value("${spring.cloud.nacos.discovery.namespace}")
    public String NAMESPACE;


    public static final String NACOS = "nacos";

    public static final String MYSQL = "mysql";

}
