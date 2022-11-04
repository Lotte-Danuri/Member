package com.lotte.danuri.member.coupon;

import com.lotte.danuri.member.common.exception.codes.CommonErrorCode;
import com.lotte.danuri.member.common.exception.codes.CouponErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoMyCouponException;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.coupon.dto.MyCouponInsertReqDto;
import com.lotte.danuri.member.coupon.dto.MyCouponRespDto;
import com.lotte.danuri.member.follow.Follow;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.Store;
import com.lotte.danuri.member.store.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class MyCouponServiceImpl implements MyCouponService {

    private final MyCouponRepository myCouponRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public int saveAllCoupons(MyCouponInsertReqDto dto) {

        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS)
        );

        List<Member> followers = store.getFollowList().stream().map(Follow::getMember).toList();

        List<MyCoupon> result = new ArrayList<>();

        for(Member m : followers) {
            for(Long c : dto.getCouponList()) {
                result.add(MyCoupon.builder().couponId(c).member(m).status(1).build());
            }
        }

        myCouponRepository.saveAll(result);

        return 1;
    }

    @Override
    public Long saveCoupons(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        MyCoupon coupon = myCouponRepository.save(MyCoupon.builder()
                                                        .couponId(couponId)
                                                        .member(member)
                                                        .status(1)
                                                        .build());

        return coupon.getId();
    }

    @Override
    public List<Long> getMyCoupons(Long memberId) {
        return myCouponRepository.findByMemberIdAndDeletedDateIsNull(memberId).orElseGet(ArrayList::new)
            .stream().map(MyCoupon::getCouponId).collect(Collectors.toList());
    }

    @Override
    public boolean checkIfHasCoupon(Long memberId, Long couponId) {
        return myCouponRepository.findByMemberIdAndCouponId(memberId, couponId).isPresent();
    }

    @Override
    public List<MyCouponRespDto> getMyCouponsByStatus(Long memberId, int status) {
        return myCouponRepository.findByMemberIdAndStatusAndDeletedDateIsNull(memberId, status).orElseGet(ArrayList::new)
            .stream().map(c -> MyCouponRespDto.builder()
                .id(c.getId()).couponId(c.getCouponId()).status(c.getStatus()).build()).collect(Collectors.toList());
    }

    @Override
    public int updateStatus(Long myCouponId, int status) {
        //status : 0 - 사용불가, 1 - 사용가능
        MyCoupon coupon = myCouponRepository.findById(myCouponId).orElseThrow(
            () -> new NoMyCouponException(CouponErrorCode.NO_COUPONS_EXISTS.getMessage(), CouponErrorCode.NO_COUPONS_EXISTS)
        );
        coupon.update(status);
        return 1;
    }

    @Override
    public int delete(Long myCouponId) {
        MyCoupon coupon = myCouponRepository.findById(myCouponId).orElseThrow(
            () -> new NoMyCouponException(CouponErrorCode.NO_COUPONS_EXISTS.getMessage(), CouponErrorCode.NO_COUPONS_EXISTS)
        );
        coupon.delete();
        return 1;
    }

    @Override
    public int deleteAllByMember(Long memberId) {
        myCouponRepository.findByMemberIdAndDeletedDateIsNull(memberId).orElseGet(ArrayList::new)
            .forEach(MyCoupon::delete);

        return 1;
    }
}
