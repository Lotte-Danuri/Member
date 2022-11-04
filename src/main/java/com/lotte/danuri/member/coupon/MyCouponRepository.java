package com.lotte.danuri.member.coupon;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCouponRepository extends JpaRepository<MyCoupon, Long> {

    Optional<List<MyCoupon>> findByMemberIdAndDeletedDateIsNull(Long memberId);

    Optional<List<MyCoupon>> findByMemberIdAndStatusAndDeletedDateIsNull(Long memberId, int status);

    Optional<MyCoupon> findByMemberIdAndCouponId(Long memberId, Long couponId);
}
