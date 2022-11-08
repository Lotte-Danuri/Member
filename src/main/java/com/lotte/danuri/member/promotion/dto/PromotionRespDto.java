package com.lotte.danuri.member.promotion.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PromotionRespDto {

    private String title;
    private String imageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String buttonUrl;

}
