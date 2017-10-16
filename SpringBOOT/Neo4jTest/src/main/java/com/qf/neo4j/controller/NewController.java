/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.controller;

import com.qf.neo4j.nodeEntity.Person;
import com.qf.neo4j.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: NewController
 * @Description:
 * @author: zhbo
 * @date: 2017/10/16 15:53
 * @Copyright: 2017 . All rights reserved.
 */
@RestController
public class NewController {
    @Autowired
    PersonRepository personRepository;
    @RequestMapping(value = "newperson", method = RequestMethod.GET)
    public String newPerson(String name){
        Person pe = personRepository.getPersonByName(name);
        pe.setAge(44);
        pe.setName("山岭巨人");
        pe = new Person();
        pe.setName("小小");
        pe.setAge(22);
        personRepository.save(pe); //新增
        pe = personRepository.getPersonByName("山岭巨人");
            return "name:"+ pe.getName() + "age:" + pe.getAge();
    }
}
