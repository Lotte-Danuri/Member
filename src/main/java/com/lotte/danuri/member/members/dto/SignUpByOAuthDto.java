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
public class SignUpByOAuthDto {
    private String name;
    private int role;
    private String gender;
    private String birthday;

    public Member toEntity() {
        return Member.builder()
            .name(name)
            .role(role)
            .gender(gender)
            .birthDate(birthday)
            .build();
    }

}
