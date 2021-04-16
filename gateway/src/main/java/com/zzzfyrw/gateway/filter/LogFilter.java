package com.zzzfyrw.gateway.filter;

import com.google.gson.JsonObject;
import com.zzzfyrw.common.constant.CommonConstant;
import com.zzzfyrw.common.constant.HeaderConstant;
import com.zzzfyrw.common.json.gson.GsonUtil;
import com.zzzfyrw.common.utils.CommonUtils;
import com.zzzfyrw.common.utils.IPv4Utils;
import com.zzzfyrw.gateway.constant.FilterConstant;
import com.zzzfyrw.gateway.mono.child.LogHandler;
import com.zzzfyrw.gateway.translate.RequestLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        RequestLogVo vo = new RequestLogVo();

        long start = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();

        String ip = IPv4Utils.getIpAddress(request);
        Long ipAton = IPv4Utils.ipAton(ip);

        HttpMethod method = request.getMethod();
        String contentType = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        URI uri = request.getURI();
        AtomicReference<String> requestParams = new AtomicReference<>("");
        HttpHeaders headers = request.getHeaders();
        String requestId =  headers.getFirst(HeaderConstant.REQUEST_ID);
        if(null == requestId){
            requestId = CommonUtils.get32UUID(true);
        }
        String finalRequestId = requestId;
        ServerHttpRequest addRequest = request.mutate().headers(x -> {
            x.add(HeaderConstant.REQUEST_ID, finalRequestId);
        }).build();

        Mono<Void> voidMono = Mono.empty();

        long time = 0l;

        vo.setCreatedType(CommonConstant.USER_TYPE_PLANT);
        vo.setRequestId(finalRequestId);
        vo.setCreatedTime(LocalDateTime.now());
        vo.setContentType(contentType);
        vo.setRequestUrl(uri.getPath());
        vo.setIpInt(ipAton);
        try {
            log.info("=================== REQUEST START ======================");
            log.info("==== REQUEST ID :{} ======",requestId);
            switch (Objects.requireNonNull(method)){
                case GET:
                    MultiValueMap<String, String> queryParams = addRequest.getQueryParams();
                    JsonObject getParams = new JsonObject();
                    Set<Map.Entry<String, List<String>>> entries = queryParams.entrySet();
                    for (Map.Entry<String, List<String>> entry : entries) {
                        if(null != entry.getKey()){
                            String value = entry.getValue().get(0);
                            if(null == value || "".equals(value.trim())){
                                value = "";
                            }
                            getParams.addProperty(entry.getKey(),value);
                        }
                    }
                    requestParams.set(GsonUtil.fromObjectToJson(getParams));
                    time = System.currentTimeMillis() - start;
                    vo.setRequestParams(requestParams.toString());
                    vo.setTime(time);
                    vo.setRequestType(method.name());

                    voidMono = chain.filter(exchange
                            .mutate()
                            .request(addRequest)
                            .build())
                            .doFirst(new LogHandler(vo));

                    break;
                case DELETE:
                case PUT:
                case POST:
                    if(CommonUtils.isUploadFile(contentType)){
                        requestParams.set("上传文件");
                    }else {
                        voidMono = DataBufferUtils.join(addRequest.getBody())
                                .flatMap(dataBuffer -> {
                                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(bytes);
                                    MediaType mediaType = headers.getContentType();
                                    requestParams.set(new String(bytes, CommonUtils.getMediaTypeCharset(mediaType)));
                                    DataBufferUtils.release(dataBuffer);
                                    Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                                        DataBuffer buffer = exchange.getResponse().bufferFactory()
                                                .wrap(bytes);
                                        return Mono.just(buffer);
                                    });
                                    ServerHttpRequest writeRequest = new ServerHttpRequestDecorator(
                                            addRequest) {
                                        @Override
                                        public Flux<DataBuffer> getBody() {
                                            return cachedFlux;
                                        }
                                    };
                                    return chain.filter(exchange
                                            .mutate()
                                            .request(writeRequest)
                                            .build())
                                            .doFirst(new LogHandler(vo));
                                });

                    }
                    time = System.currentTimeMillis() - start;
                    vo.setTime(time);
                    vo.setRequestType(method.name());
                    vo.setRequestParams(requestParams.toString());
                    break;
                default:
                    log.info("NOT SUPPORT REQUEST METHOD : {}",method.name());
                    break;
            }
        }finally {
            log.info("【url : {}】,【content-type : {}】",uri.getPath(),contentType);
            log.info("【request params : {}】",requestParams);
            log.info("============ time : {} ms ==================",time);
            log.info("==== REQUEST ID :{} ======",requestId);
            log.info("=================== REQUEST END ========================");
        }

        return voidMono;

    }

    @Override
    public int getOrder() {
        return FilterConstant.LOG_FILTER;
    }




}
