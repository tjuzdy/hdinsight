package com.bigdata.hdinsight.service;

import com.bigdata.hdinsight.api.pto.TopicCreatePto;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.List;
import java.util.Set;

public interface KafkaService {

    void listen(String message);

    List<NewTopic> topicsCreate(TopicCreatePto topicCreatePto);

    KafkaFuture<Set<String>> topicsList();

    KafkaAdmin admin();

    AdminClient adminClient();
}
