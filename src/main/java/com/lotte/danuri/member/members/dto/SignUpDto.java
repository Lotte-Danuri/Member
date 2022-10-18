package com.lotte.danuri.member.members.dto;

import com.lotte.danuri.member.members.Member;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpDto {

    @NotNull
    @Size(min = 5, max = 12)
    private String id;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String gender;

    private int role;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String birthDate;

    public Member toEntity() {
        return Member.builder()
            .address(address)
            .gender(gender)
            .name(name)
            .phoneNumber(phoneNumber)
            .role(role)
            .birthDate(birthDate)
            .build();
    }

}
