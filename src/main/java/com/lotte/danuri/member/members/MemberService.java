package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    long updateMemberInfo(MemberInfoReqDto dto);

    long updateSellerAuth(SellerAuthReqDto dto);

    Optional<List<Member>> getSellers(SellerAuthReqDto dto);


}
