package com.lotte.danuri.member.store;

import com.lotte.danuri.member.domain.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand")
public class Brand extends BaseEntity {

    private String name;
    private String image;
    private LocalDateTime deletedDate;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Store> storeList;
}
