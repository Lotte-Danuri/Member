package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public long updateMemberInfo(MemberInfoReqDto dto) {

        Member findMember = memberRepository.findById(dto.getId()).orElseThrow();
        findMember.updateInfo(dto.getName(), dto.getAddress(), dto.getPhoneNumber());

        Member updatedMember = memberRepository.save(findMember);
        return updatedMember.getId();
    }

    @Override
    public long updateSellerAuth(SellerAuthReqDto dto) {

        Member findMember = memberRepository.findById(dto.getMemberId()).orElseThrow();
        findMember.updateStatus(dto.getStatus());

        Member updatedMember = memberRepository.save(findMember);
        return updatedMember.getId();
    }

    @Override
    public List<Member> getSellers(SellerAuthReqDto dto) {

        // role : 2 -> seller, status : 1 -> 등록, 0 -> 비등록(대기중)
        List<Member> memberList = memberRepository.findByRoleAndStatus(dto.getRole(), dto.getStatus())
                                                    .orElseGet(ArrayList::new);
        return memberList;
    }
}
