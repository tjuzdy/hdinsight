package com.bigdata.hdinsight.api.pto;

import lombok.Data;

@Data
public class TopicCreatePto {

    private String name;

    private int numPartitions;

    private short replicationFactor;

}
