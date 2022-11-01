package com.lotte.danuri.member.members;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.follow.Follow;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity()
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    private String gender;
    private String phoneNumber;
    private String address;
    private String birthDate;
    private String name;

    private int role;
    private int status;

    private LocalDateTime deletedDate;

    private int memberShipRank;

    private LocalDateTime registeredDate;

    private LocalDateTime expiredDate;

    private Long storeId;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followList;

    public void updateStatus(int status) {
        this.status = status;
    }

    public void updateSeller(Long storeId) {
        this.storeId = storeId;
    }

    public void updateInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void updateFollows(Follow follow) {
        if(!this.followList.contains(follow)) {
            this.followList.add(follow);
        }
    }

    public void delete() {
        this.deletedDate = LocalDateTime.now();
    }

    public void updateMemberShip(int rank) {
        this.memberShipRank = rank;
        this.registeredDate = LocalDateTime.now();
        this.expiredDate = LocalDateTime.now().plusYears(1);
    }

}
