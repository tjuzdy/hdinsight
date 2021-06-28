package com.bigdata.hdinsight.service.impl;

import com.bigdata.hdinsight.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    @KafkaListener(topics = "testTopic")
    public void listen(String message) {
        log.info("KafkaListener listen message: " + message);
    }

}
