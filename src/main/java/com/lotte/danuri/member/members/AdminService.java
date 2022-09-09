package com.lotte.danuri.member.members;


import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import com.lotte.danuri.member.members.dto.SellerRespDto;
import java.util.List;

public interface AdminService {

    long updateSellerAuth(SellerAuthReqDto dto);

    List<SellerRespDto> getSellers(SellerAuthReqDto dto);
}
