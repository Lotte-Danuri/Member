package com.lotte.danuri.member.members;


import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberServiceImpl memberService;

    @Test
    void 회원정보수정() {

        long memberId = 1L;

        MemberInfoReqDto dto = new MemberInfoReqDto(1L, "안채영", "경기도 부천시", "01011111111");

        long result = memberService.updateMemberInfo(dto);

        Assertions.assertEquals(memberId, result);
    }

    @Test
    void 판매자_등록수정삭제() {

        long memberId = 1L;

        SellerAuthReqDto dto = new SellerAuthReqDto(1L, 2, 1);

        long result = memberService.updateSellerAuth(dto);

        Assertions.assertEquals(memberId, result);
    }
}
