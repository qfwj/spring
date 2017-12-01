/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.controller;

import com.qf.neo4j.nodeEntity.Movie;
import com.qf.neo4j.nodeEntity.Person;
import com.qf.neo4j.repository.MovieRepository;
import com.qf.neo4j.repository.PersonRepository;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.ogm.response.model.QueryResultModel;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @Autowired
    Session session;



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
        queryAndResolverRelaByNode("father");
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

    private Map queryAndResolverRelaByNode(String name) {
        String cypher_rela;
//        String cypher_rela = "MATCH r=(:" + status + "{code:'" + code + "'})-[:" + status + "*]->() RETURN r";
       /* String cypher_rela = "MATCH r=({code:'" + name + "', status:'" + status + "'})-[:" + status + "*]->() RETURN r";*/
        //如果是实时的话，需要从neo4j取发布和未发布状态的所有关系
        //将此结点出去的，和进来的关系合并返回
        cypher_rela = "MATCH ps=()-[*]->({code:'" + name + "'}) RETURN ps as p UNION ALL MATCH ps2=()<-[*]-({name:'" + name + "'}) RETURN ps2 as p";

        QueryResultModel _neoEntity = (QueryResultModel) session.query(cypher_rela, new HashMap<>());
        Set _nodes = nodesResolver(_neoEntity);
        Set _relas = relasResolver(_neoEntity);
        Map _obj = new HashMap();
        _obj.put("nodes", _nodes);
        _obj.put("relationships", _relas);
        return _obj;
    }

    private Set nodesResolver(Iterable<Map<String, Object>> queryResults) {
        Set nodes = new HashSet();

        if (null == queryResults) {
            return nodes;
        }
        queryResults.forEach((map) -> {
            map.entrySet().forEach((entry) -> {
                InternalPath internalPath = (InternalPath) entry.getValue();
                internalPath.nodes().forEach((n) -> {
                    Map node = new HashMap();
                    InternalNode _n = (InternalNode) n;
                    node.put("id", _n.id());
                    node.put("labels", _n.labels());
                    node.put("properties", _n.asMap());
                    if (null != _n.asMap()) {
                        if (null != _n.asMap().get("version")) {
                            //加一个索引字段，用来搜索code加version
                            node.put("idx", _n.asMap().get("code").toString() + "v" + _n.asMap().get("version").toString());
                        } else {
                            //加一个索引字段，用来搜索code加version
                            node.put("idx", _n.asMap().get("code").toString());
                        }
                    }
                    nodes.add(node);
                });
            });
        });
        return nodes;

    }

    private Set relasResolver(Iterable<Map<String, Object>> queryResults) {
        Set relas = new HashSet();

        queryResults.forEach((map) -> {
            map.entrySet().forEach((entry) -> {
                InternalPath internalPath = (InternalPath) entry.getValue();
                internalPath.relationships().forEach(r -> {
                    InternalRelationship _r = (InternalRelationship) r;
                    Map rela = new HashMap();
                    rela.put("id", _r.id());
                    rela.put("start", _r.startNodeId());
                    rela.put("end", _r.endNodeId());
                    rela.put("type", _r.type());
                    relas.add(rela);
                });
            });
        });

        return relas;

    }

}
