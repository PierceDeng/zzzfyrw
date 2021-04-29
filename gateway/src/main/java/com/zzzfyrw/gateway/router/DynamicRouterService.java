package com.zzzfyrw.gateway.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DynamicRouterService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Autowired
    private InMemoryRouteDefinitionRepository repository;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    /**
     * 增加路由
     *
     * @param definition
     * @return
     */
    public synchronized String add(RouteDefinition definition) {
        log.info("gateway add route {}", definition);
        try {
            repository.save(Mono.just(definition)).subscribe();
        } catch (Exception e) {

        }
        return "success";
    }

    /**
     * 更新路由
     *
     * @param definition
     * @return
     */
    public synchronized String update(RouteDefinition definition) {
        try {
            log.info("gateway update route {}", definition);
            delete(definition.getId());
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            repository.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route fail";
        }
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public synchronized Mono<ResponseEntity<Object>> delete(String id) {
        return this.repository.delete(Mono.just(id))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume((t) -> t instanceof NotFoundException,
                        (t) -> Mono.just(ResponseEntity.notFound().build()));
    }


}
