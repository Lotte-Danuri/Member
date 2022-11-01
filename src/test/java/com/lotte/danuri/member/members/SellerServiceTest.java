package com.lotte.danuri.member.members;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SellerServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    void 셀러지점정보등록() {

        Long memberId = 85L;

        memberService.updateSeller(memberId, 9L);

        Member member = memberRepository.findById(memberId).get();
        System.out.println("updated storeId = " + member.getStoreId());

        assertThat(member.getStoreId()).isEqualTo(9L);
    }

    @Test
    void 셀러지점정보등록_실패_권한없는경우() {

        Long memberId = 4L;

        assertThatThrownBy(() -> memberService.updateSeller(memberId, 9L))
            .isInstanceOf(NoMemberException.class)
            .hasMessageContaining("Seller is not Existed");
    }

    @Test
    void 셀러지점정보등록_실패_지점번호() {

        Long memberId = 102L;

        assertThatThrownBy(() -> memberService.updateSeller(memberId, 1L))
            .isInstanceOf(NoStoreException.class)
            .hasMessageContaining("Store is not existed");
    }
}
