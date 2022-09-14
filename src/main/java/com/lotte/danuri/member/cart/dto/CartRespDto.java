package com.lotte.danuri.member.cart.dto;

import com.lotte.danuri.member.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartRespDto {

    private long productId;

    private int quantity;

}
