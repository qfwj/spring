/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.repository;

import com.qf.neo4j.nodeEntity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PersonRepository
 * @Description:
 * @author: zhbo
 * @date: 2017/10/16 17:37
 * @Copyright: 2017 . All rights reserved.
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {
    @Query("MATCH (user:person {name:{name}}) RETURN user")

    /*@Query("match(n:person) return n")*/
    Person getPersonByName(@Param("name") String name);
    @Query("match(n:grandfather)-[r *1..2]-(m) return n,m,r")
    List<Person> getGrandfather(@Param("name") String name);

    @Query("match(n:father)-[r *1..2]-(m) return n,m,r")
    List<Person> getFatherby(@Param("name") String name);

    @Query("match(n:wife)-[r *1..2]-(m) return n,m,r")
    List<Person> getwifeby(@Param("name") String name);

    @Query("match(n:son)-[r *1..2]-(m) return n,m,r")
    List<Person> getsonby(@Param("name") String name);

    //Person findByNameAndAge(String name, int age);

    @Query("MATCH (user:person {name:{name}}) delete user")

    /*@Query("match(n:person) return n")*/
    void deletePersonByName(@Param("name") String name);

}



