package com.lotte.danuri.member.members;


import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import java.util.List;

public interface AdminService {

    long updateSellerAuth(SellerAuthReqDto dto);

    List<Member> getSellers(SellerAuthReqDto dto);
}
