package com.lotte.danuri.member.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartListRespDto {

    private int quantity;

    private ProductDto productDto;
}
