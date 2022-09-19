package com.lotte.danuri.member.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyCouponRespDto {

    private Long id;

    private Long couponId;

    private int status;

}
