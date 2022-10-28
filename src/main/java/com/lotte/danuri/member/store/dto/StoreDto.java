package com.lotte.danuri.member.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDto {

    private Long id;

    private Long sellerId;

    private String name;

    private String description;

    private String address;

    private String ownerNumber;

    public Store toEntity(Member member) {
        return Store.builder()
            .name(name)
            .description(description)
            .address(address)
            .ownerNumber(ownerNumber)
            .build();
    }

}
