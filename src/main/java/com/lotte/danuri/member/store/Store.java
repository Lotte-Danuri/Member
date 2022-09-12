package com.lotte.danuri.member.store;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.store.dto.StoreDto;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Store extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String name;

    private String descrption;

    private String address;

    private double score;

    private String ownerName;

    private String ownerNumber;

    private String image;

    public StoreDto toDto() {
        return StoreDto.builder()
            .sellerId(member.getId())
            .name(name)
            .address(address)
            .description(descrption)
            .ownerName(ownerName)
            .ownerNumber(ownerNumber)
            .image(image)
            .score(score)
            .build();
    }

    public void update(StoreDto dto) {
        this.name = dto.getName();
        this.descrption = dto.getDescription();
        this.address = dto.getAddress();
        this.ownerName = dto.getOwnerName();
        this.ownerNumber = dto.getOwnerNumber();
    }
}
