package com.lotte.danuri.member.likes.dto;

import com.lotte.danuri.member.likes.Likes;
import com.lotte.danuri.member.members.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
public class LikesInsertReqDto {

    private Long memberId;

    private Long productId;

    public Likes toEntity(Member member) {
        return Likes.builder()
            .member(member)
            .productId(productId)
            .build();
    }
}
