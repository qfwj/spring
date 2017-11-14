/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.controller;

import com.qf.neo4j.nodeEntity.Movie;
import com.qf.neo4j.nodeEntity.Person;
import com.qf.neo4j.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: NewService
 * @Description:
 * @author: zhbo
 * @date: 2017/10/23 16:18
 * @Copyright: 2017 . All rights reserved.
 */
@Service
public class NewService {
    @Autowired
    MovieRepository movieRepository;
    @Transactional
    public String set(String title){
//Person pe = personRepository.getPersonByName(diresctor);
        Set set = new HashSet<String>();
        set.add("魔兽世界");
        set.add("冰封王座");
        Movie movie = movieRepository.findOne(new Long(29));
        movie.setDirector(null);
        Person pe =new Person();
        //pe.setAge(99);
        pe.setName("test3");
        List list = new ArrayList();
        list.add(pe);
        movie.setDirector(list);
        movie.setTitle(title);


        movieRepository.save(movie);
        movie = movieRepository.getByTitle("魔兽世界");
        return "sss";
    }
}
