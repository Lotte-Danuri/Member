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
    private Long memberId;
    private Long productId;
    private Boolean isInsert;

    public LikesReqDto(Long id, Long memberId, Long productId, Boolean isInsert) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
        this.isInsert = isInsert;
    }

    public Likes toEntity(Member member) {
        return Likes.builder()
            .member(member)
            .productId(productId)
            .build();
    }

}
