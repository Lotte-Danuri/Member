package com.lotte.danuri.member.kafka.service;

import java.util.Map;

public interface KafkaConsumerService {

    Map<Object, Object> kafkaInit(String kafkaMessage);
    void insertMyCoupon(String kafkaMessage);

}
