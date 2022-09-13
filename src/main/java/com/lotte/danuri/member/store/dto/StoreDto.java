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
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDto {

    private long id;

    private long sellerId;

    private String name;

    private String description;

    private String address;

    private String ownerName;

    private String ownerNumber;

    private String image;

    private double score;

    public Store toEntity(Member member) {
        return Store.builder()
            .member(member)
            .name(name)
            .descrption(description)
            .address(address)
            .score(score)
            .ownerName(ownerName)
            .ownerNumber(ownerNumber)
            .image(image)
            .build();
    }

}
