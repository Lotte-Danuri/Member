package com.lotte.danuri.member.follow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lotte.danuri.member.follow.Follow;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowDto {

    private Long id;
    private Long storeId;

}
