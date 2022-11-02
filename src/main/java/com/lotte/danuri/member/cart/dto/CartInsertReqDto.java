package com.lotte.danuri.member.cart.dto;

import com.lotte.danuri.member.cart.Cart;
import com.lotte.danuri.member.members.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartInsertReqDto {

    private Long productId;

    private int quantity;

    private Member member;

    public Cart toEntity(Member member) {
        return Cart.builder()
            .member(member)
            .productId(productId)
            .quantity(quantity)
            .build();
    }
}
