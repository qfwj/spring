/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.nodeEntity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @ClassName: Movie
 * @Description:
 * @author: zhbo
 * @date: 2017/10/16 15:36
 * @Copyright: 2017 . All rights reserved.
 */

@NodeEntity(label =  "movie")
public class Movie {
    @GraphId
    Long id;
    String title;

    @Relationship(type = "从属", direction = Relationship.OUTGOING)
    Person director;

    /**
     * 获取 title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 director
     *
     * @return director
     */
    public Person getDirector() {
        return director;
    }

    /**
     * 设置 director
     */
    public void setDirector(Person director) {
        this.director = director;
    }

/*    *//**
     * 获取 actors
     *
     * @return actors
     *//*
    public Set<Person> getActors() {
        return actors;
    }

    *//**
     * 设置 actors
     *//*
    public void setActors(Set<Person> actors) {
        this.actors = actors;
    }

    @Relationship(type = "ACTS_IN", direction = Relationship.INCOMING)
    Set<Person> actors;*/

    /*@Relationship(type = "RATED")
    List<Rating> ratings;*/
}
