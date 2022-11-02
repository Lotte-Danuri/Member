package com.lotte.danuri.member.kafka.service;

import com.lotte.danuri.member.likes.dto.LikesReqDto;

public interface KafkaProducerService {
    LikesReqDto send(String topic, LikesReqDto likesReqDto);
}
