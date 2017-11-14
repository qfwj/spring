/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.controller;

import com.qf.neo4j.nodeEntity.Movie;
import com.qf.neo4j.nodeEntity.Person;
import com.qf.neo4j.repository.MovieRepository;
import com.qf.neo4j.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    NewService newService;



    /**
     * @Author: zhbo
     * @method: newPerson
     * @Description: 创建新的label为person的节点，属性name为
     * @param [name]
     * @return: java.lang.String
     * @throws:
     * @date:  2017-10-16 19:15:016
     */
    @RequestMapping(value = "newperson", method = RequestMethod.GET)
    public String newPerson(String name) {

        List list = personRepository.getGrandfather("");




        list = personRepository.getFatherby("");


        list = personRepository.getwifeby("");


        list = personRepository.getsonby("");

        Person pe = personRepository.getPersonByName(name);

        pe.setName("山岭巨人");
        pe = new Person();
        pe.setName("小小");

        personRepository.save(pe); //新增
        pe = personRepository.getPersonByName("山岭巨人");
        return "name:" + pe.getName() + "age:";
    }


    /**
     * @Author: zhbo
     * @method: deletePerson
     * @Description: 根据节点的name属性删除person
     * @param [name]
     * @return: java.lang.String
     * @throws:
     * @date:  2017-10-16 19:14:037
     */
    @RequestMapping(value = "deleteperson", method = RequestMethod.GET)
    public String deletePerson(String name) {
        personRepository.deletePersonByName(name);
        return "name:" + name;
    }




    @RequestMapping(value="newmovie", method = RequestMethod.GET)
    public String newMovie(String title, String diresctor){
       // Person pe = personRepository.findByNameAndAge(diresctor, 0);
        //pe = personRepository.findByNameAndAge(diresctor, 1);
/*
        Person pe =new Person();
        pe.setAge(77);
        pe.setName("test1");

        List list = new ArrayList();
        list.add(pe);
        Set set = new HashSet<String>();
        set.add("魔兽世界");
        set.add("冰封王座");
        Movie movie = movieRepository.findOne(new Long(29));
        movie.setDirector(list);
        movie.setTitle(title);


        movieRepository.save(movie);
        movie = movieRepository.getByTitle("魔兽世界");*/

        return newService.set(title);
    }
}
