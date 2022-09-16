package com.lotte.danuri.member.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartUpdateReqDto {

    private Long id;

    private int quantity;

}
