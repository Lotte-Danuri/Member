package com.lotte.danuri.member.cart;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private Long productId;

    private int quantity;

    public void update(int quantity) {
        this.quantity = quantity;
    }

}
