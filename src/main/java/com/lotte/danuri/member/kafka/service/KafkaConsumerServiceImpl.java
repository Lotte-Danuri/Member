package com.lotte.danuri.member.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotte.danuri.member.cart.CartRepository;
import com.lotte.danuri.member.common.exception.codes.ErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.coupon.MyCoupon;
import com.lotte.danuri.member.coupon.MyCouponRepository;
import com.lotte.danuri.member.follow.Follow;
import com.lotte.danuri.member.follow.FollowRepository;
import com.lotte.danuri.member.store.Store;
import com.lotte.danuri.member.store.StoreRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class KafkaConsumerServiceImpl implements  KafkaConsumerService{

    private final MyCouponRepository myCouponRepository;
    private final FollowRepository followRepository;
    private final StoreRepository storeRepository;
    private final CartRepository cartRepository;

    @Override
    public Map<Object, Object> kafkaInit(String kafkaMessage){
        log.info("Kafka Message : => "+ kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
        return map;
    }

    @Override
    @KafkaListener(topics = "coupon-insert")
    public void insertMyCoupon(String kafkaMessage){

        Map<Object, Object> msgInfo = kafkaInit(kafkaMessage);

        Long couponId = Long.valueOf(String.valueOf(msgInfo.get("id")));
        Long storeId = Long.valueOf(String.valueOf(msgInfo.get("storeId")));

        storeRepository.findByIdAndDeletedDateIsNull(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(),
                                        StoreErrorCode.NO_STORE_EXISTS)
        );

        List<Follow> follows = followRepository.findByStoreId(storeId).orElseThrow();

        follows.forEach(f -> {
            MyCoupon coupon = MyCoupon.builder().member(f.getMember()).couponId(couponId).status(0).build();
            myCouponRepository.save(coupon);
            log.info("[Kafka] MyCouponInsert save MyCoupons : {}", coupon.getCouponId());
        });

    }

    @Override
    @KafkaListener(topics = "order-insert-cart-delete")
    public void deleteCart(String kafkaMessage) {

        Map<Object, Object> msgInfo = kafkaInit(kafkaMessage);

        log.info("kafkaMessage msgInfo = {}", msgInfo);
        Long memberId = Long.valueOf(String.valueOf(msgInfo.get("buyerId")));

        cartRepository.deleteAllByMemberId(memberId);
        log.info("[Kafka] CartDelete delete cart");

    }
}
