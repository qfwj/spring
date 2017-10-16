/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: PersistenceContext
 * @Description:
 * @author: zhbo
 * @date: 2017/10/16 15:58
 * @Copyright: 2017 . All rights reserved.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.qf.neo4j")
@EnableNeo4jRepositories("com.qf.neo4j.repository")
public class PersistenceContext {

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(configuration(), "com.qf.neo4j.nodeEntity");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://neo4j:hanlei128@192.168.239.128:7474");
        return config;
    }
}