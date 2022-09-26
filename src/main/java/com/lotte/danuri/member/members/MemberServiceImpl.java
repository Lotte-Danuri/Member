package com.lotte.danuri.member.members;

import com.lotte.danuri.member.cart.CartRepository;
import com.lotte.danuri.member.common.exception.codes.ErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.SellerStoreExistedException;
import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import com.lotte.danuri.member.store.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Long updateMemberInfo(MemberInfoReqDto dto) {

        Member findMember = memberRepository.findById(dto.getId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );
        findMember.updateInfo(dto.getName(), dto.getAddress(), dto.getPhoneNumber());

        return findMember.getId();
    }

    @Override
    public MemberRespDto getMember(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        return MemberRespDto.builder()
            .id(member.getId())
            .name(member.getName())
            .address(member.getAddress())
            .age(member.getAge())
            .phoneNumber(member.getPhoneNumber())
            .build();

    }

    @Override
    public int delete(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        int role = member.getRole();
        if(role == 2 && storeRepository.findByMemberIdAndDeletedDateIsNull(member.getId()).isPresent()) {
            throw new SellerStoreExistedException(MemberErrorCode.SELLER_STORE_EXISTS.getMessage(), MemberErrorCode.SELLER_STORE_EXISTS);
        }

        member.delete();

        return 0;
    }

    @Override
    public Long updateMemberShip(Long memberId, int rank) {

        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        member.updateMemberShip(rank);

        return member.getId();
    }

}
