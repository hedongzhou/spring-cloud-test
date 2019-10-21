package com.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Hello
 *
 * @author hedongzhou
 * @since 2019/10/14
 */
@FeignClient(name = "nacos-discovery",
        fallback = HelloFeignFallback.class,
        configuration = FeignConfiguration.class)
public interface HelloFeign {

    /**
     * hello
     *
     * @param name
     * @return
     */
    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);

}

/**
 * config
 */
class FeignConfiguration {

    @Bean
    public HelloFeignFallback helloFeignFallback() {
        return new HelloFeignFallback();
    }

}

/**
 * fall back
 */
class HelloFeignFallback implements HelloFeign {

    /**
     * hello
     *
     * @param name
     * @return
     */
    @Override
    public String hello(String name) {
        return "error";
    }

}
