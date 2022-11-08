package com.lotte.danuri.member.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StoreInfoRespDto {

    private Long storeId;
    private String storeName;
    private Long brandId;
    private String brandName;

}
