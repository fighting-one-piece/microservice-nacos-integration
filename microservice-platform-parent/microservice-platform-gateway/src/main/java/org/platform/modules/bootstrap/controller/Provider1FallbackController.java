package org.platform.modules.bootstrap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Provider1FallbackController {

    @RequestMapping("/microservice-platform-nacos-provider1/fallback")
    public Mono<String> microservicePlatformNacosProvider1Fallback() {
        return Mono.just("micro service platform nacos provider1 unavailable, jump to fallback handle");
    }

}