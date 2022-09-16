package com.lotte.danuri.member.members.dto;

import com.lotte.danuri.member.members.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoReqDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;


}
