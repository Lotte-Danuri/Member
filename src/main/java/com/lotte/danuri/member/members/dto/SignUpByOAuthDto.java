package com.lotte.danuri.member.members.dto;

import com.lotte.danuri.member.members.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpByOAuthDto {
    private String name;
    private String gender;
    private String birthday;
    private String phone;

    public Member toEntity() {
        return Member.builder()
            .name(name)
            .role(0)
            .gender(gender)
            .birthDate(birthday)
            .phoneNumber(phone)
            .build();
    }

    public void update() {
        if(birthday.contains("-")) {
            birthday = birthday.replace("-", "");
        }

        if(gender.startsWith("f") || gender.startsWith("F")) {
            gender = "여자";
        }else if(gender.startsWith("m") || gender.startsWith("M")) {
            gender = "남자";
        }
    }

}
