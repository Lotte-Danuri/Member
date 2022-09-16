package com.lotte.danuri.member.members.dto;

import com.lotte.danuri.member.members.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SellerAuthReqDto {

    private Long memberId;
    private int role;
    private int status;

    public SellerAuthReqDto(int role, int status) {
        this.role = role;
        this.status = status;
    }

    public void update(Member member) {
        member.updateStatus(status);
    }

}
