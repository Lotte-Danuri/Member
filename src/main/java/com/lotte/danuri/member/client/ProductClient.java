package com.lotte.danuri.member.client;

import com.lotte.danuri.member.client.dto.CouponReqDto;
import com.lotte.danuri.member.client.dto.CouponRespDto;
import com.lotte.danuri.member.client.dto.ProductDto;
import com.lotte.danuri.member.client.dto.ProductListDto;
import com.lotte.danuri.member.client.dto.ProductListByCodeDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/products/productId")
    List<ProductDto> getProductListById(@RequestBody ProductListDto dto);

    @PostMapping("/products/productCode")
    List<ProductDto> getProductListByCode(@RequestBody ProductListByCodeDto dto);

    @PostMapping("/admin/coupons/list")
    List<CouponRespDto> getCoupons(@RequestBody CouponReqDto dto);

}
