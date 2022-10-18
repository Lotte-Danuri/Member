package com.lotte.danuri.member.client.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderDataDto {

    private Long id;
    private String sellerId;
    private Long productId;
    private String productName;
    private Long productQuantity;
    private Long productPrice;
    private LocalDateTime warrantyStartDate;
    private LocalDateTime warrantyEndDate;
    private String thumbnail;

}
