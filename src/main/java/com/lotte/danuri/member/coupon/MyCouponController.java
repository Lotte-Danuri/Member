package com.lotte.danuri.member.coupon;

import com.lotte.danuri.member.coupon.dto.MyCouponInsertReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponRespDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mycoupon")
@AllArgsConstructor
public class MyCouponController {

    private final MyCouponService myCouponService;

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody MyCouponInsertReqDto dto) {
        // dto에 coupon id list Product 서버에 API 통신해서 받아와야함.
        return new ResponseEntity<>(myCouponService.saveAllCoupons(dto), HttpStatus.OK);
    }

    // 모든 조회 메소드 추후 수정 필요 : 마이쿠폰에서 쿠폰 목록 데이터 가져온 후 Product 서버에서 쿠폰 정보 요청 필요

    @GetMapping("/all")
    public ResponseEntity<?> getAllCoupons(@RequestBody MyCouponReqDto dto) {
        List<MyCouponRespDto> result = myCouponService.getMyCoupons(dto.getMemberId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getCoupons(@RequestBody MyCouponReqDto dto) {
        List<MyCouponRespDto> result = myCouponService.getMyCouponsByStatus(dto.getMemberId(), dto.getStatus());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> update(@RequestBody MyCouponReqDto dto) {
        return new ResponseEntity<>(myCouponService.updateStatus(dto.getId(), dto.getStatus()), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<?> delete(@RequestBody MyCouponReqDto dto) {
        return new ResponseEntity<>(myCouponService.delete(dto.getId()), HttpStatus.OK);
    }
}
