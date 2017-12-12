package com.qf.kafkaestest.service;



import com.qf.kafkaestest.config.EsProperties;
import com.qf.kafkaestest.constant.EsConstant;
import com.qf.kafkaestest.constant.EsEnum;
import com.qf.kafkaestest.constant.KafkaConsant;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName: Esservice
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 14:34
 * @Copyright: 2017 . All rights reserved.
 */
@Component
public class EsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsService.class);

    @Autowired
    private TransportClient client;
    @Autowired
    private EsProperties esProperties;


    /**
     * @param [json]
     * @Author: zhbo
     * @Description: saveToEs 保存文档到es
     * @return: java.lang.String
     * @throws:
     * @date: 2017-12-04 10:41:22
     */
    public String saveToEs(List<Map<String, String>> datamap) {

        try {
            List mainProcessList = new ArrayList();
            /*找出主流程*/
            datamap.forEach(f ->
            {
                /*主流程时进行查询删除*/
                if (EsConstant.PARENT_EMPTY.equals(f.get(EsConstant.PARENT)) && f.get(EsConstant.TYPE).equals(EsEnum.PROCESS.strName)) {
                    mainProcessList.add(f);
                    LOGGER.info("add mainProcess:{} into list", f.get(KafkaConsant.ESUNIQUEID));
                }
            });
            if (!mainProcessList.isEmpty())
                LOGGER.info("response:{} for deleting es data by uuid:{}", SearchAndDelete(mainProcessList));

            /*利用bulk进行批量操作*/
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            datamap.forEach(
                    f -> {
                        IndexRequestBuilder builder = client.prepareIndex(esProperties.getIndex(), f.get(EsConstant.TYPE), f.get(EsConstant.DOCUMENTID));
                        if (!f.get(EsConstant.TYPE).equals(EsEnum.PROCESS.strName)) {
                            builder.setRouting(f.get(EsConstant.PARENT))
                                    .setParent(f.get(EsConstant.PARENT));
                        }
                        bulkRequest.add(
                                builder.setSource(f.get(EsConstant.ESDATA), XContentType.JSON)
                        );
                    }
            );


            BulkResponse bulkResponse = bulkRequest.get();

            LOGGER.info("repsonse{} for saving data to es", bulkResponse.status());
        } catch (Exception e) {
            LOGGER.error("error for saving to es:{} ", e);
            return null;
        }

        return "success";
    }


    /**
     * @param [map]
     * @Author: zhbo
     * @Description: SearchAndDelete 查出与map中esUniqueId相等、traceId不等的文档，然后根据文档的traceId将所有的process、model删除
     * @return: java.lang.String
     * @throws:
     * @date: 2017-12-04 18:56:49
     */
    public String SearchAndDelete(List<Map> mapList) throws Exception {
        /*进行批量查询*/
        MultiSearchRequestBuilder multiSearchRequestBuilder = client.prepareMultiSearch();
        mapList.forEach(
                f -> {
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(EsConstant.TRACEID, f.get(EsConstant.TRACEID)))
                            .must(QueryBuilders.termQuery(EsConstant.PARENT, f.get(EsConstant.PARENT)))
                            .filter(QueryBuilders.termQuery(EsConstant.ESUNIQUEID, f.get(EsConstant.ESUNIQUEID)));
                    SearchRequestBuilder searchRequestBuilder = client.prepareSearch(esProperties.getIndex())
                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                            .setQuery(boolQueryBuilder);
                    multiSearchRequestBuilder.add(searchRequestBuilder);
                }
        );
        MultiSearchResponse sr = multiSearchRequestBuilder.get();

        if (sr.getResponses().length > 0) {
            Set<Object> traceSet = new HashSet<Object>();
            sr.forEach(
                    f -> f.getResponse().getHits().forEach(n -> {
                                traceSet.add(n.getSource().get(EsConstant.TRACEID)); //获取需要删除的traceId
                            }
                    )
            );

         /*直接根据查询语句traceId进行批量删除*/
            BulkByScrollResponse response =
                    DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                            .filter(QueryBuilders.termsQuery(EsConstant.TRACEID, traceSet))
                            .source("persons")
                            .get();
        }


        return "success";
    }

}
