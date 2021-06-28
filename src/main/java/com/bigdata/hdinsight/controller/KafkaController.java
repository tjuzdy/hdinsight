package com.bigdata.hdinsight.controller;

import com.bigdata.hdinsight.api.pto.ProducerSendPto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/kafka/v1")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping(value = "/producer/send", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public Object send(@RequestBody ProducerSendPto producerSendPto) {
        try {
            kafkaTemplate.send(producerSendPto.getTopicName(), producerSendPto.getPartition(), producerSendPto.getKey(), producerSendPto.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producerSendPto.getValue();
    }

}
