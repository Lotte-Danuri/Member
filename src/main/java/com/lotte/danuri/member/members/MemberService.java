package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import com.lotte.danuri.member.members.dto.SignUpByOAuthDto;
import com.lotte.danuri.member.members.dto.SignUpDto;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long register(SignUpDto dto);

    Long registerByOAuth(SignUpByOAuthDto dto);

    Long updateSeller(Long memberId, Long storeId);

    Long getSeller(Long memberId);

    Long updateMemberInfo(Long memberId, MemberInfoReqDto dto);

    MemberRespDto getMember(Long memberId);

    int delete(Long memberId);

    Long updateMemberShip(String memberId, int rank);

}
