package com.example.qfcloud.service;

import org.springframework.stereotype.Component;

/**
 * Created by puhui on 2017/5/3.
 */
@Component

public class SchedualServiceHiHystric implements FeignService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry from Feigin, "+name;
    }
}
