package com.test.controller;

import com.test.feign.HelloFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello
 *
 * @author hedongzhou
 * @since 2019/10/06
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloFeign helloFeign;

    /**
     * hello
     *
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello, " + name;
    }

    /**
     * say hello
     *
     * @return
     */
    @GetMapping("/say_hello")
    public String sayHello(String name) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", name);
        return restTemplate.getForObject("http://nacos-discovery/hello?name={name}", String.class, params);
    }

    /**
     * say hello2
     *
     * @return
     */
    @GetMapping("/say_hello2")
    public String sayHello2(String name) {
        return helloFeign.hello(name);
    }

}
