package com.example.qfcloud.Controller;

import com.example.qfcloud.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by puhui on 2017/5/3.
 */
@RestController
public class HelloService {
    @Autowired
    HystrixService hello_Service;
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return hello_Service.hiService(name);
    }
}
