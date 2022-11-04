package com.lotte.danuri.member.coupon;

import com.lotte.danuri.member.client.ProductClient;
import com.lotte.danuri.member.client.dto.CouponDto;
import com.lotte.danuri.member.client.dto.CouponListDto;
import com.lotte.danuri.member.client.dto.CouponReqDto;
import com.lotte.danuri.member.client.dto.CouponRespDto;
import com.lotte.danuri.member.coupon.dto.MyCouponInsertReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponRespDto;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/mycoupon")
public class MyCouponController {

    private final MyCouponService myCouponService;
    private final ProductClient productClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public MyCouponController(MyCouponService myCouponService, ProductClient productClient,
        CircuitBreakerFactory circuitBreakerFactory) {
        this.myCouponService = myCouponService;
        this.productClient = productClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @PostMapping("/all")
    @ApiOperation(value = "쿠폰 추가", notes = "마이 쿠폰 목록에 쿠폰 추가")
    public ResponseEntity<?> save(@RequestBody MyCouponInsertReqDto dto) {
        return new ResponseEntity<>(myCouponService.saveAllCoupons(dto), HttpStatus.CREATED);
    }

    @PostMapping("/person")
    @ApiOperation(value = "쿠폰 저장", notes = "개인 회원에게 쿠폰 발급 및 저장")
    public ResponseEntity<?> insert(@RequestHeader String memberId,
                                    @RequestBody MyCouponReqDto dto) {
        Long result =
            myCouponService.saveCoupons(Long.parseLong(memberId), dto.getId());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // 모든 조회 메소드 추후 수정 필요 : 마이쿠폰에서 쿠폰 목록 데이터 가져온 후 Product 서버에서 쿠폰 정보 요청 필요

    @GetMapping("/all")
    @ApiOperation(value = "쿠폰 조회", notes = "마이쿠폰함에 있는 모든 쿠폰 조회")
    public ResponseEntity<?> getAllCoupons(@RequestHeader String memberId) {
        List<Long> result = myCouponService.getMyCoupons(Long.parseLong(memberId));

        log.info("Before Call [getCoupons] Method IN [Product-Service]");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        List<CouponRespDto> resultList = circuitBreaker.run(() ->
            productClient.getCoupons(CouponReqDto.builder().couponId(result).build()),
            throwable -> new ArrayList<>());
        log.info("After Call [getCoupons] Method IN [Product-Service]");

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping("/check")
    @ApiOperation(value = "쿠폰 보유 여부 조회", notes = "채팅방에서 쿠폰 수락 여부 표시를 위한 보유 여부 조회")
    public ResponseEntity<?> check(@RequestBody MyCouponReqDto dto) {
        log.info("Before Retrieve [checkIfHasCoupon] Method IN [MyCoupon-Service]");
        boolean result = myCouponService.checkIfHasCoupon(dto.getMemberId(), dto.getId());
        log.info("After Retrieve [checkIfHasCoupon] Method IN [MyCoupon-Service]");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/status")
    @ApiOperation(value = "쿠폰 조회", notes = "마이쿠폰함에 있는 쿠폰 상태별 조회")
    public ResponseEntity<?> getCoupons(@RequestHeader String memberId,
                                        @RequestBody MyCouponReqDto dto) {

        ///////////// productClient 사용 필요
        List<MyCouponRespDto> result = myCouponService.getMyCouponsByStatus(Long.parseLong(memberId), dto.getStatus());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/status")
    @ApiOperation(value = "쿠폰 수정", notes = "마이쿠폰함에 있는 쿠폰 상태 수정(대기중 -> 사용가능)")
    public ResponseEntity<?> update(@RequestBody MyCouponReqDto dto) {
        return new ResponseEntity<>(myCouponService.updateStatus(dto.getId(), dto.getStatus()), HttpStatus.OK);
    }

    @PatchMapping()
    @ApiOperation(value = "쿠폰 삭제", notes = "마이쿠폰함에서 쿠폰 삭제")
    public ResponseEntity<?> delete(@RequestBody MyCouponReqDto dto) {
        return new ResponseEntity<>(myCouponService.delete(dto.getId()), HttpStatus.OK);
    }

    @PostMapping("/product")
    @ApiOperation(value = "상품 적용 가능한 쿠폰 조회", notes = "마이쿠폰함에 현재 구매할 상품에 적용 가능한 쿠폰을 가지고 있는지 조회")
    public ResponseEntity<?> getProductCoupons(@RequestHeader String memberId,
                                                @RequestBody MyCouponReqDto dto) {

        List<Long> couponIdList = myCouponService.getMyCoupons(Long.parseLong(memberId));
        if(!couponIdList.isEmpty()) {

            log.info("Before Call [getCouponDetailList] Method IN [Product-Service]");
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
            List<CouponDto> result = circuitBreaker.run(() ->
                productClient.getCouponDetailList(CouponListDto.builder()
                                                        .couponId(couponIdList)
                                                        .productId(dto.getProductId())
                                                        .build()),
                throwable -> new ArrayList<>());
            log.info("After Call [getCouponDetailList] Method IN [Product-Service]");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
