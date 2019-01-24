package com.niuzj.ribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {
        //顺序填充占位符
//        return restTemplate.getForEntity("http://provider/hello?name={1}", String.class, "nzj").getBody();
        //使用key-value填充占位符
        Map<String, String> map = new HashMap<>();
        map.put("name", "nzj");
        ResponseEntity<String> entity = restTemplate.getForEntity("http://provider/hello?name={name}", String.class, map);
        System.out.println("status:" + entity.getStatusCode());
        System.out.println(entity.getHeaders());
        return entity.getBody();

    }
}
