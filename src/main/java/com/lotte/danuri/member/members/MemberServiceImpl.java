package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
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

    @Override
    public long updateMemberInfo(MemberInfoReqDto dto) {

        Member findMember = memberRepository.findById(dto.getId()).orElseThrow();
        findMember.updateInfo(dto.getName(), dto.getAddress(), dto.getPhoneNumber());

        return findMember.getId();
    }
}
