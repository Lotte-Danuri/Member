package com.lotte.danuri.member.likes.dto;

import com.lotte.danuri.member.likes.Likes;
import com.lotte.danuri.member.members.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
public class LikesReqDto {

    private Long id;
    private String productCode;

    public LikesReqDto(Long id, String productCode) {
        this.id = id;
        this.productCode = productCode;
    }

    public Likes toEntity(Member member) {
        return Likes.builder()
            .member(member)
            .productCode(productCode)
            .build();
    }

}
