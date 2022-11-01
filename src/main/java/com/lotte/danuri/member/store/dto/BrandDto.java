package com.lotte.danuri.member.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BrandDto {

    private Long id;
    private String name;
    private String imageUrl;
}
