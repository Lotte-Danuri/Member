package com.lotte.danuri.member.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartReqDto {

    private long memberId;

}
