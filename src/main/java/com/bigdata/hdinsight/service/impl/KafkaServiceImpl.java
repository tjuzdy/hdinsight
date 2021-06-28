package com.bigdata.hdinsight.service.impl;

import com.bigdata.hdinsight.api.pto.TopicCreatePto;
import com.bigdata.hdinsight.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @KafkaListener(topics = "testTopic")
    public void listen(String message) {
        log.info("KafkaListener listen message: " + message);
    }

    @Override
    public List<NewTopic> topicsCreate(TopicCreatePto topicCreatePto) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        AdminClient adminClient = AdminClient.create(configs);
        NewTopic newTopic = new NewTopic(topicCreatePto.getName(), topicCreatePto.getNumPartitions(), (short) topicCreatePto.getReplicationFactor());
        List<NewTopic> topicList = Arrays.asList(newTopic);
        adminClient.createTopics(topicList);
        adminClient.close(10, TimeUnit.SECONDS);
        log.info("======创建 "+topicCreatePto.getName()+" 成功======");
        return topicList;
    }

    @Override
    public KafkaFuture<Set<String>> topicsList() {
        ListTopicsResult result = adminClient().listTopics();
        KafkaFuture<Set<String>> names = result.names();
        try {
            names.get().forEach((name)->{
                log.info(name);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        adminClient().close();
        return names;
    }

    @Override
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Override
    public AdminClient adminClient() {
        return AdminClient.create(admin().getConfigurationProperties());
    }

}
