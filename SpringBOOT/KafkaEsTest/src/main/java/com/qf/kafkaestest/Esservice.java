/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest;

import com.qf.kafkaestest.constant.EsConstant;
import com.qf.kafkaestest.constant.EsEnum;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public String saveToEs(Map<String, String> json){
        try{
            /*主流程时进行查询删除*/
            if(StringUtils.isEmpty(json.get("parent")) && json.get("type").equals(EsEnum.PROCESS.name));
            SearchAndDelete(json);
            IndexResponse response = client.prepareIndex(esProperties.getIndex(), json.get("type"), json.get("id"))
                    .setSource(json.get("data"), XContentType.JSON)
                    .get();
        } catch (Exception e) {
            return null;
        }

        return "success";
    }

    private  String SearchAndDelete(Map<String, String> json){
        QueryBuilders.boolQuery();
        String esUniqueId = json.get(EsConstant.ESUNIQUEID);
        SearchResponse response = client.prepareSearch(esProperties.getIndex())
                //.setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery(EsConstant.ESUNIQUEID, json.get("EsUniqueNo")))                 // Query
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();

    }
}
