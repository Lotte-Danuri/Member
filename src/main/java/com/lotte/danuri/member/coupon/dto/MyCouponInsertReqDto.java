package com.lotte.danuri.member.coupon.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyCouponInsertReqDto {

    private Long storeId;

    private List<Long> couponList;

}
