package com.lotte.danuri.member.coupon;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "my_coupon")
public class MyCoupon extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "Member_id")
    private Member member;

    private Long couponId;

    private LocalDateTime deletedDate;

    private int status;

    public void update(int status) {
        this.status = status;
    }

    public void delete() {
        this.deletedDate = LocalDateTime.now();
    }
}
