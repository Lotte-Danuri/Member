package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final MemberRepository memberRepository;

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
