package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity(name="likes")
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Likes extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private Long productId;

}
