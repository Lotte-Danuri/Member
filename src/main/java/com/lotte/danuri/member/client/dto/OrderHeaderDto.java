package com.lotte.danuri.member.client.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderHeaderDto {

    // OrderHeader
    private Long id;
    private String buyerId;
    private Long totalPrice;
    private Long totalQuantity;
    private String address1;
    private String address2;
    private LocalDateTime orderDate;

    List<OrderDataDto> orderDataDtoList;

}
