package com.lotte.danuri.member.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FollowRespDto {

    private Long id;
    private Boolean status;

}
