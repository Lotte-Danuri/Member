package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
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
    public int updateSellerAuth(SellerAuthReqDto dto) {
        return 0;
    }

    @Override
    public Optional<List<Member>> getSellers(SellerAuthReqDto dto) {
        return Optional.empty();
    }
}
