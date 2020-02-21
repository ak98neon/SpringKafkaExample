package com.oracle.ofsc.service.kafkadispatcher.kafka.model;

public class RoutingTopicData {
    private String topic;
    private int partition;
    private int lag;

    public RoutingTopicData(String topic, int partition, int lag) {
        this.topic = topic;
        this.partition = partition;
        this.lag = lag;
    }

    public String getTopic() {
        return topic;
    }

    public int getPartition() {
        return partition;
    }

    public int getLag() {
        return lag;
    }
}
