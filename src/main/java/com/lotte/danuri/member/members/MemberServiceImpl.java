package com.lotte.danuri.member.members;

import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.common.exception.exceptions.SellerStoreExistedException;
import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.members.dto.SignUpByOAuthDto;
import com.lotte.danuri.member.members.dto.SignUpDto;
import com.lotte.danuri.member.store.StoreRepository;
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
    public Long register(SignUpDto dto) {
        Member member = memberRepository.save(dto.toEntity());
        return member.getId();
    }

    @Override
    public Long registerByOAuth(SignUpByOAuthDto dto) {
        dto.update();
        Member member = memberRepository.save(dto.toEntity());
        return member.getId();
    }

    @Override
    public Long updateSeller(Long memberId, Long storeId) {
        Member seller = memberRepository.findByIdAndRole(memberId, 1).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_SELLER_EXISTS.getMessage(), MemberErrorCode.NO_SELLER_EXISTS)
        );

        storeRepository.findById(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS)
        );
        seller.updateSeller(storeId);

        return memberId;

    }

    @Override
    public Long getSeller(Long memberId) {
        Member seller = memberRepository.findByIdAndRole(memberId, 1).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_SELLER_EXISTS.getMessage(), MemberErrorCode.NO_SELLER_EXISTS)
        );

        return seller.getStoreId();
    }

    @Override
    public Long updateMemberInfo(MemberInfoReqDto dto) {

        Member findMember = memberRepository.findByIdAndDeletedDateIsNull(dto.getId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );
        findMember.updateInfo(dto.getName(), dto.getAddress(), dto.getPhoneNumber());

        return findMember.getId();
    }

    @Override
    public MemberRespDto getMember(Long memberId) {

        Member member = memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        return MemberRespDto.builder()
            .id(member.getId())
            .name(member.getName())
            .address(member.getAddress())
            .birthDate(member.getBirthDate())
            .phoneNumber(member.getPhoneNumber())
            .build();

    }

    @Override
    public int delete(Long memberId) {

        Member member = memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );


        member.delete();

        return 0;
    }

    @Override
    public Long updateMemberShip(String memberId, int rank) {

        Member member = memberRepository.findByIdAndDeletedDateIsNull(Long.parseLong(memberId)).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        member.updateMemberShip(rank);

        return member.getId();
    }

}
