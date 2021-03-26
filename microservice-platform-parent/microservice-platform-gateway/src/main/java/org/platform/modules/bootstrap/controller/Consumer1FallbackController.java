package org.platform.modules.bootstrap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Consumer1FallbackController {

    @RequestMapping("/microservice-platform-nacos-consumer1/fallback")
    public Mono<String> microservicePlatformNacosConsumer1Fallback() {
        return Mono.just("micro service platform nacos consumer1 unavailable, jump to fallback handle");
    }

}