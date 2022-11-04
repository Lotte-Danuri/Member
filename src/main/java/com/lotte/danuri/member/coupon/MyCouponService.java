package com.lotte.danuri.member.coupon;

import com.lotte.danuri.member.coupon.dto.MyCouponInsertReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponRespDto;
import java.util.List;

public interface MyCouponService {

    int saveAllCoupons(MyCouponInsertReqDto dto);

    Long saveCoupons(Long memberId, Long couponId);

    List<Long> getMyCoupons(Long memberId);

    boolean checkIfHasCoupon(Long memberId, Long couponId);

    List<MyCouponRespDto> getMyCouponsByStatus(Long memberId, int status);

    int updateStatus(Long myCouponId, int status);

    int delete(Long myCouponId);

    int deleteAllByMember(Long memberId);
}
