package com.lotte.danuri.member.promotion;

import com.lotte.danuri.member.domain.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
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
@Table(name = "promotion")
public class Promotion extends BaseEntity {

    private String title;
    private String imageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String buttonUrl;
    private LocalDateTime deletedDate;

}
