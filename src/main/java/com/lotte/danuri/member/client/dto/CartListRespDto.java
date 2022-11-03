package com.lotte.danuri.member.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartListRespDto {

    private Long id;

    private int quantity;

    private ProductDto productDto;

    private String storeName;

    private String brandName;

}
