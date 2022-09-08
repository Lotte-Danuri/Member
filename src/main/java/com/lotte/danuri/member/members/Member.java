package com.lotte.danuri.member.members;

import com.lotte.danuri.member.domain.BaseEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="member")
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    private long authId;

    private String gender;
    private String phoneNumber;
    private String address;
    private int age;
    private String name;

    private int role;
    private int status;

    public void updateStatus(int status) {
        this.status = status;
    }

    public void updateInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
