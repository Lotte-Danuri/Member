package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.store.Store;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Follow extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

    private LocalDateTime deletedDate;

    public void delete() {
        this.deletedDate = LocalDateTime.now();
    }
}
