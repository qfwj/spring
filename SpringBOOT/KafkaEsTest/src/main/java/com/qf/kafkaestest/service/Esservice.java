/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest.service;


import com.qf.kafkaestest.config.EsProperties;
import com.qf.kafkaestest.constant.EsConstant;
import com.qf.kafkaestest.constant.EsEnum;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.util.Map;

/**
 * @ClassName: Esservice
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 14:34
 * @Copyright: 2017 . All rights reserved.
 */
@Component
public class Esservice {
    @Autowired
    private TransportClient client;
    @Autowired
    private EsProperties esProperties;

    public String saveToEs(Map<String, String> json) {
        try {

            /*主流程时进行查询删除*/
            if (StringUtils.isEmpty(json.get("parent")) && json.get("type").equals(EsEnum.PROCESS.name)) ;
            SearchAndDelete(json);
            IndexResponse response = client.prepareIndex(esProperties.getIndex(), json.get("type"), json.get("id"))
                    .setSource(json.get("data"), XContentType.JSON)
                    .get();
        } catch (Exception e) {
            return null;
        }

        return "success";
    }

    private String SearchAndDelete(Map<String, String> json) {
        QueryBuilders.boolQuery();
        String esUniqueId = json.get(EsConstant.ESUNIQUEID);
        SearchResponse response = client.prepareSearch(esProperties.getIndex())
                //.setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery(EsConstant.ESUNIQUEID, json.get("EsUniqueNo")))                 // Query
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        return null;
    }



    public String searchAndDeleteTest(Map<String, String> json) throws Exception {

        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
               .put("cluster.name", "escluster")
                .build();
        InetAddress ss = InetAddress.getByName("192.168.30.130");
        ss = InetAddress.getLocalHost();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.30.130"), 9300));


       BoolQueryBuilder boolQueryBuilder =  QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery("addr", "beijing"))
               .filter(QueryBuilders.termQuery("name", "xiaoxiao"));



        SearchResponse response = client.prepareSearch().get();
        //String esUniqueId = json.get(EsConstant.ESUNIQUEID);
         response = client.prepareSearch("es")
                //.setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();

         SearchHits searchHits = response.getHits();
         /*根据Id去删除*/
         searchHits.forEach(f->{
             //client.prepareDelete("es",f.getType(), f.getId()).get();
         });

         /*直接根据查询语句删除*/
        BulkByScrollResponse response1 =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(boolQueryBuilder)
                        .source("es")
                        .get();

        long deleted = response1.getDeleted();
        return null;
    }

    public static void main(String[] args) throws Exception {
        new Esservice().searchAndDeleteTest(null);
    }
}
