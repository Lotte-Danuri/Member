package com.lotte.danuri.member.promotion.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PromotionListRespDto {

    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
