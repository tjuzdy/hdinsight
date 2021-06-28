package com.bigdata.hdinsight.api.pto;

import lombok.Data;

@Data
public class TopicCreatePto {

    private String name;

    private int numPatitions;

    private short replicationFactor;

}
