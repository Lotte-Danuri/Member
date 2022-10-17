package com.lotte.danuri.member.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberRespDto {

    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    private int age;

    private int role;

    private String birthDate;

}
