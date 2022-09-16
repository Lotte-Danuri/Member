package com.lotte.danuri.member.store;

import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.follow.Follow;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Store extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String name;

    private String description;

    private String address;

    private Double score;

    private String ownerName;

    private String ownerNumber;

    private String image;

    private LocalDateTime deletedDate;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followList;

    public StoreDto toDto() {
        return StoreDto.builder()
            .id(getId())
            .sellerId(member.getId())
            .name(name)
            .address(address)
            .description(description)
            .ownerName(ownerName)
            .ownerNumber(ownerNumber)
            .image(image)
            .score(score)
            .build();
    }

    public void update(StoreDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.address = dto.getAddress();
        this.ownerName = dto.getOwnerName();
        this.ownerNumber = dto.getOwnerNumber();
    }

    public void delete() {
        this.deletedDate = LocalDateTime.now();
    }
}
