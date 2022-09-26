package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long updateMemberInfo(MemberInfoReqDto dto);

    MemberRespDto getMember(Long memberId);

    int delete(Long memberId);

    Long updateMemberShip(Long memberId, int rank);

}
