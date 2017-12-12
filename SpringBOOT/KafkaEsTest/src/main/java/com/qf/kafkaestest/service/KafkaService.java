package com.qf.kafkaestest.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qf.kafkaestest.constant.EsConstant;
import com.qf.kafkaestest.constant.EsEnum;
import com.qf.kafkaestest.constant.KafkaConsant;
import com.qf.kafkaestest.constant.KafkaEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: KafkaEsTest
 * @Description:
 * @author: zhbo
 * @date: 2017/11/29 17:59
 * @Copyright: 2017 . All rights reserved.
 */
@Service
public class KafkaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);
    @Autowired
    private KafkaConsumer kafkaConsumer;
    @Autowired
    private EsService esservice;

    /**
     * @Author: zhbo
     * @Description: getKafka 从kafka获取数据
     *  @param []
     * @return: java.lang.String
     * @throws:
     * @date: 2017-12-04 10:46:58
     */
    @PostConstruct
    public String getKafka() {
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            /*sync处理，避免内存激增*/
            List temp = this.kafka2EsList(records);
            if (temp != null)
                LOGGER.info("response for saving data to es :{}", esservice.saveToEs(temp));
        }
    }


    /**
     * @Author: zhbo
     * @Description: kafka2Es kafka数据转换
     * @param [kafkadata]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @throws:
     * @date: 2017-12-04 10:46:08
     */
    private List<Map> kafka2EsList(ConsumerRecords<String, String> kafkadata) {
        List<Map> list = new ArrayList<>(kafkadata.count());
        for (ConsumerRecord<String, String> record : kafkadata) {
            LOGGER.info(record.value());
            Map temp = this.kafkaRecord2EsMap(record.value());
            if (temp != null)
                list.add(temp);
        }
        return list;
    }

    /**
     * @Author: zhbo
     * @Description: kafkaRecord2EsMap kafka
     * @param [kafkadataStr]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @throws:
     * @date:  2017-12-07 19:07:37
     */

    private Map<String, Object> kafkaRecord2EsMap(String kafkadataStr) {
        try {
            JSONObject json = JSON.parseObject(kafkadataStr);
            String logtype = json.get(KafkaConsant.LOGTYPE).toString();
            if (!isNeedSave(logtype))
                return null;

            Map<String, Object> map = new HashMap<>(6);
            map.put(EsConstant.TRACEID, json.get(EsConstant.TRACEID));

            /*
            * es中只存放processes、models两类文档
            * */
            if (logtype.equals(KafkaEnum.PROCESSES.strName)) {
                map.put(EsConstant.TYPE, EsEnum.PROCESS.strName); //设置文档类型
                map.put(EsConstant.PARENT, json.get(EsConstant.PARENT)); //设置process+的parent
                map.put(EsConstant.DOCUMENTID, json.get(KafkaConsant.PROCESSDOCUMENTID)); //文档id
            } else {
                map.put(EsConstant.PARENT, json.get(KafkaConsant.PESID)); //设置model的parent
                map.put(EsConstant.TYPE, EsEnum.MODELS.strName); //设置文档类型
                map.put(EsConstant.DOCUMENTID, json.get(KafkaConsant.MODLEDOCUMENTID)); //文档id
            }

            /*根据渠道确定Es唯一码前缀*/
            map.put(EsConstant.ESUNIQUEID, json.get(KafkaConsant.ESUNIQUEID));
            /*es存放的data*/
            map.put(EsConstant.ESDATA, json.toJSONString());
            return map;
        } catch (Exception e) {
            LOGGER.error("json数据：{} 转换异常", kafkadataStr, e);
            return null;
        }


    }

    /**
     * @param [type]
     * @Author: zhbo
     * @Description: isNeedSave 根据日志类型判断是否需要存储
     * @return: boolean
     * @throws:
     * @date: 2017-12-04 12:12:03
     */
    private boolean isNeedSave(String type) {

        if (type.equals(KafkaEnum.GBDTMODELS.name())
                || type.equals(KafkaEnum.TREEMODELS.strName)
                || type.equals(KafkaEnum.PROCESSES.strName)
                || type.equals(KafkaEnum.RULEMODELS.strName)
                || type.equals(KafkaEnum.PYTHONMODELS.strName)
                )
            return true;
        return false;
    }

}
