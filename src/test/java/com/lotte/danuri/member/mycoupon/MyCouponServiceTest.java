package com.lotte.danuri.member.mycoupon;

import static org.assertj.core.api.Assertions.assertThat;

import com.lotte.danuri.member.coupon.MyCouponService;
import com.lotte.danuri.member.coupon.dto.MyCouponInsertReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponRespDto;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MyCouponServiceTest {

    @Autowired
    MyCouponService myCouponService;

    @Test
    void 마이쿠폰_생성() {

        List<Long> couponList = new ArrayList<>();
        couponList.add(1L);
        couponList.add(2L);
        couponList.add(3L);

        int result = myCouponService.saveAllCoupons(MyCouponInsertReqDto.builder().couponList(couponList).storeId(11L).build());

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 마이쿠폰_전체조회() {

        Long memberId = 5L;

        List<Long> list = myCouponService.getMyCoupons(memberId);

        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 마이쿠폰_상태별조회() {

        Long memberId = 5L;
        int status = 1;

        List<MyCouponRespDto> list = myCouponService.getMyCouponsByStatus(memberId, status);
        list.forEach(c -> System.out.println(c.getId() + " " + c.getStatus()));

        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 마이쿠폰_상태_수정() {

        Long myCouponId = 4L;
        int status = 1;

        int result = myCouponService.updateStatus(myCouponId, status);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 마이쿠폰_삭제() {

        Long myCouponId = 5L;

        int result = myCouponService.delete(myCouponId);

        assertThat(result).isEqualTo(1);
    }
}
