package com.niuzj.ribbonconsumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.niuzj.ribbonconsumer.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class HelloServiceImpl implements IHelloService {

    @Autowired
    private RestTemplate restTemplate;

    private String result = "success";

    @Override
    @HystrixCommand(fallbackMethod = "error")//开启熔断,指定服务调用失败时要调用的方法
    public String helloService() {
        restTemplate.put("http://provider/user/{1}", new HashMap<>(), "2");
        return result;
    }


    public String error(){
        return "error";
    }
}
