package com.lotte.danuri.member.members;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import com.lotte.danuri.member.members.dto.SellerRespDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AdminServiceTest {

    @Autowired
    AdminServiceImpl adminService;

    @Test
    void 관리자_셀러_등록수정삭제() {

        long memberId = 1L;

        SellerAuthReqDto dto = new SellerAuthReqDto(1L, 2, 1);

        long result = adminService.updateSellerAuth(dto);

        assertEquals(memberId, result);
    }

    @Test
    void 관리자_셀러_조회_등록() {

        SellerAuthReqDto dto = new SellerAuthReqDto(2,1);
        List<SellerRespDto> list =  adminService.getSellers(dto);

        list.forEach(member -> System.out.println(member.getName()));
        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }

    @Test
    void 관리자_셀러_조회_비등록() {

        SellerAuthReqDto dto = new SellerAuthReqDto(2,0);
        List<SellerRespDto> list =  adminService.getSellers(dto);

        list.forEach(member -> System.out.println(member.getName()));
        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }
}
