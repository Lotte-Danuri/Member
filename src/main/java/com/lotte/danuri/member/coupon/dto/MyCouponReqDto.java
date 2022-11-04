package com.lotte.danuri.member.coupon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyCouponReqDto {

    private Long id;

    private Long productId;

    private int status;

    private Long memberId;

}
