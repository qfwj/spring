/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.zookeeper.serviceregistry.ServiceInstanceRegistration;
import org.springframework.cloud.zookeeper.serviceregistry.ZookeeperRegistration;
import org.springframework.cloud.zookeeper.serviceregistry.ZookeeperServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ZookeeperController
 * @Description:
 * @author: zhbo
 * @date: 2017/7/15 15:37
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping(value = "/test")
public class ZookeeperController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ZookeeperServiceRegistry serviceRegistry;
    @RequestMapping(value = "/show", method = {RequestMethod.GET})
    @ResponseBody
    public String show(@RequestParam(value="id") Long id) {


        List<ServiceInstance> list = discoveryClient.getInstances("server");
        ServiceInstance service = list.get(0);
        service.getUri();
        /*if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().toString();
        }*/
        ZookeeperRegistration registration = ServiceInstanceRegistration.builder()
                .defaultUriSpec()
                .address("anyUrl")
                .port(10)
                .name("/a/b/c/d/anotherservice")
                .build();
        serviceRegistry.register(registration);

        return null;
      /*  ServiceInstance instance = client.getLocalServiceInstance();
        ServerDTO dto = new ServerDTO();
        dto.setId(id);
        dto.setName("scott");
        dto.setAddress("北京");
        return dto;*/
    }
}

