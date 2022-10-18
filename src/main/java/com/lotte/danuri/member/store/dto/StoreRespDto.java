package com.lotte.danuri.member.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class StoreRespDto {

    private Long storeId;
    private String storeName;

}
