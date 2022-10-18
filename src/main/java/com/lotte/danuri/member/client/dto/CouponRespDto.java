package com.lotte.danuri.member.client.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@AllArgsConstructor
public class CouponRespDto {

    private Long id;
    private Long storeId;
    private String name;
    private String contents;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    private Double discountRate;
    private Double minOrderPrice;
    private Double maxDiscountPrice;

    private List<Long> productId;

}
