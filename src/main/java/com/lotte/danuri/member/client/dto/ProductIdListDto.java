package com.lotte.danuri.member.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductIdListDto {

    private List<Long> productIdList;
}
