package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import com.lotte.danuri.member.members.dto.SignUpDto;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long register(SignUpDto dto);

    Long updateMemberInfo(MemberInfoReqDto dto);

    MemberRespDto getMember(Long memberId);

    int delete(Long memberId);

    Long updateMemberShip(String memberId, int rank);

}
