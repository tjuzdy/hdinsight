package com.bigdata.hdinsight.controller;

import com.bigdata.hdinsight.api.pto.ProducerSendPto;
import com.bigdata.hdinsight.api.pto.TopicCreatePto;
import com.bigdata.hdinsight.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/kafka/v1")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaService kafkaService;

    @RequestMapping(value = "/producer/send", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public Object send(@RequestBody ProducerSendPto producerSendPto) {
        try {
            kafkaTemplate.send(producerSendPto.getTopicName(), producerSendPto.getPartition(), producerSendPto.getKey(), producerSendPto.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producerSendPto.getValue();
    }

    @RequestMapping(value = "topics/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public List<NewTopic> topicsCreate(@RequestBody TopicCreatePto topicCreatePto) {
        List<NewTopic> topicList = kafkaService.topicsCreate(topicCreatePto);
        return topicList;
    }

    @RequestMapping(value = "topics/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public KafkaFuture<Set<String>> topicsList() {
        KafkaFuture<Set<String>> names = kafkaService.topicsList();
        return names;
    }

}
