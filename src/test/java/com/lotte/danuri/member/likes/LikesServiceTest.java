package com.lotte.danuri.member.likes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LikesServiceTest {

    @Autowired
    LikesService likesService;

    @Test
    void 상품_찜_회원아이디_조회() {

        String memberId = "113";

        List<String> resultList = likesService.getLikes(memberId);

        resultList.forEach(System.out::println);
        assertThat(resultList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 상품_찜_등록_성공() {

        Long memberId = 113L;
        String productCode = "3112301010";

        int result = likesService.register(memberId, productCode);

        assertThat(result).isEqualTo(1);

    }

    @Test
    void 상품_좋아요_확인_O() {

        Long memberId = 113L;
        String productCode = "3112301010";

        boolean result = likesService.checkLike(memberId, productCode);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void 상품_찜_등록_실패() {

        Long memberId = 100L;
        String productCode = "3112301010";

        assertThatThrownBy(() -> likesService.register(memberId, productCode))
            .isInstanceOf(NoMemberException.class)
            .hasMessageContaining("Member is not Existed");
    }

    @Test
    void 상품_찜_취소_삭제() {

        Long memberId = 113L;
        String productCode = "3112301010";

        int result = likesService.delete(memberId, productCode);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 상품_좋아요_확인_X() {

        Long memberId = 113L;
        String productCode = "3112301010";

        boolean result = likesService.checkLike(memberId, productCode);

        assertThat(result).isEqualTo(false);
    }


}
