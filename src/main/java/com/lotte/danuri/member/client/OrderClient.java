package com.lotte.danuri.member.client;

import com.lotte.danuri.member.client.dto.OrderHeaderDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order")
public interface OrderClient {

    @PostMapping("/orders/pays/list")
    List<OrderHeaderDto> getOrders(@RequestBody OrderHeaderDto dto);

    @PostMapping("/orders/pays/total")
    Long getOrdersPrice(@RequestBody OrderHeaderDto dto);
}
