package com.lotte.danuri.member.members;

import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import com.lotte.danuri.member.members.dto.SellerRespDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final MemberRepository memberRepository;

    @Override
    public Long updateSellerAuth(SellerAuthReqDto dto) {

        Member findMember = memberRepository.findByIdAndDeletedDateIsNull(dto.getMemberId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );
        findMember.updateStatus(dto.getStatus());

        Member updatedMember = memberRepository.save(findMember);
        return updatedMember.getId();
    }

    @Override
    public List<SellerRespDto> getSellers(SellerAuthReqDto dto) {

        // role : 2 -> seller, status : 1 -> 등록, 0 -> 비등록(대기중)
        List<Member> memberList = memberRepository.findByRoleAndStatus(dto.getRole(), dto.getStatus())
            .orElseGet(ArrayList::new);

        return memberList.stream().map(m -> new SellerRespDto(m.getId(), m.getName()))
            .collect(Collectors.toList());

    }
}
