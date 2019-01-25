package com.niuzj.ribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET请求
     */
    @RequestMapping("/hello")
    public String hello() {
        //顺序填充占位符
//        return restTemplate.getForEntity("http://provider/hello?name={1}", String.class, "nzj").getBody();
        //使用key-value填充占位符
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "nzj");
//        ResponseEntity<String> entity = restTemplate.getForEntity("http://provider/hello?name={name}", String.class, map);
//        System.out.println("status:" + entity.getStatusCode());
//        System.out.println("headers:" + entity.getHeaders());
        //getForObject方法直接返回实体
        String url = "http://provider/hello?name=nzj";
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * POST请求
     * 把参数放到请求体里
     */
    @RequestMapping("/hello2")
    public String hello2(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "nzj");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setDate(System.currentTimeMillis());
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<String> entity = restTemplate.postForEntity("http://provider/hello", httpEntity, String.class);
        return entity.getBody();
    }
}
