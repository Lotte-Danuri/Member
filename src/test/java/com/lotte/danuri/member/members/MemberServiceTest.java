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

        Long memberId = 1L;

        MemberInfoReqDto dto = new MemberInfoReqDto(1L, "안채영", "경기도 부천시", "01011111111");

        Long result = memberService.updateMemberInfo(dto);

        assertEquals(memberId, result);
    }

    @Test
    void 멤버쉽_가입_등급설정() {

        Long memberId = 6L;

        Long result = memberService.updateMemberShip(memberId, 2);

        assertEquals(memberId, result);
    }

}
