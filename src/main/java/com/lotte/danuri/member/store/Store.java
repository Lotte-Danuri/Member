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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "store")
public class Store extends BaseEntity {

    private String name;

    private String description;

    private String address;

    private String ownerNumber;

    private String image;

    private LocalDateTime deletedDate;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followList;

    @ManyToOne
    @JoinColumn(name = "Brand_id")
    private Brand brand;

    public StoreDto toDto() {
        return StoreDto.builder()
            .id(getId())
            .name(name)
            .address(address)
            .description(description)
            .ownerNumber(ownerNumber)
            .image(image)
            .build();
    }

    public void update(StoreDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.address = dto.getAddress();
        this.ownerNumber = dto.getOwnerNumber();
    }

    public void delete() {
        this.deletedDate = LocalDateTime.now();
    }
}
