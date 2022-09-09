package com.lotte.danuri.member.members;


import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

        assertEquals(memberId, result);
    }

    @Test
    void 관리자_셀러_등록수정삭제() {

        long memberId = 1L;

        SellerAuthReqDto dto = new SellerAuthReqDto(1L, 2, 1);

        long result = memberService.updateSellerAuth(dto);

        assertEquals(memberId, result);
    }

    @Test
    void 관리자_셀러_조회_등록() {

        SellerAuthReqDto dto = new SellerAuthReqDto(2,1);
        List<Member> list =  memberService.getSellers(dto);

        list.forEach(member -> System.out.println(member.getName()));
        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }

    @Test
    void 관리자_셀러_조회_비등록() {

        SellerAuthReqDto dto = new SellerAuthReqDto(2,0);
        List<Member> list =  memberService.getSellers(dto);

        list.forEach(member -> System.out.println(member.getName()));
        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }
}
