package com.qf.dubbox;

import com.alibaba.dubbo.config.AbstractConfig;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.remoting.transport.AbstractServer;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DubboxserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboxserverApplication.class, args);
	}
}