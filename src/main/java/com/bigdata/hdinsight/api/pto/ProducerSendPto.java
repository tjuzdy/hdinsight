package com.bigdata.hdinsight.api.pto;

import lombok.Data;

@Data
public class ProducerSendPto {

    private String topicName;

    private Integer partition;

    private String key;

    private Object value;

}
