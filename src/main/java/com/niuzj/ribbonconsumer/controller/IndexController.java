package com.niuzj.ribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://provider/hello";

    private String url2 = "http://provider/uri";

    private  String result = "success";

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
     * 请求体可以传入数据或者HttpEntity,数据可以封装在HttpEntity中
     * 服务端接受也必须使用@RequestBody注解,用实体类或者Map接收
     */
    @RequestMapping("/hello2")
    public String hello2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "nzj");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("airen", "zyq");
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(map, httpHeaders);
//
//        ResponseEntity<String> entity = restTemplate.postForEntity("http://provider/hello", httpEntity, String.class);
//        return entity.getBody();

//        return restTemplate.postForObject(url, httpEntity, String.class);

        //从header中提取Location参数构造URI
        URI uri = restTemplate.postForLocation(url2, httpEntity);
        System.out.println(uri);
        return "success";
    }

    /**
     * PUT请求
     */
    @RequestMapping("/put")
    public String put(){
        restTemplate.put("http://provider/user/{1}", new HashMap<>(), "2");
        return result;
    }

    @RequestMapping("delete")
    public String delete(){
        restTemplate.delete("http://provider/user/{1}", 2);
        return result;
    }
}
