package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "likes")
public class Likes extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "Member_id")
    private Member member;

    private Long productId;

}
