package com.lotte.danuri.member.likes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.likes.dto.LikesDeleteReqDto;
import com.lotte.danuri.member.likes.dto.LikesInsertReqDto;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import java.util.List;
import java.util.NoSuchElementException;
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
    void 찜_상품아이디_조회() {

        LikesReqDto dto = LikesReqDto.builder()
            .memberId(4L)
            .build();

        List<Long> resultList = likesService.getLikes(dto);

        resultList.forEach(System.out::println);
        assertThat(resultList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 상품_찜_등록_성공() {

        Long memberId = 4L;
        LikesReqDto dto = LikesReqDto.builder()
            .memberId(memberId)
            .productId(1L)
            .build();

        int result = likesService.register(dto);

        assertThat(result).isEqualTo(1);

    }

    @Test
    void 상품_찜_등록_실패() {

        Long memberId = 100L;
        LikesReqDto dto = LikesReqDto.builder()
            .memberId(memberId)
            .productId(2L)
            .build();

        assertThatThrownBy(() -> likesService.register(dto))
            .isInstanceOf(NoMemberException.class)
            .hasMessageContaining("Member is not Existed");
    }

    @Test
    void 상품_찜_취소() {

        LikesReqDto dto = LikesReqDto.builder()
            .id(6L).build();

        int result = likesService.delete(dto);

        assertThat(result).isEqualTo(1);
    }


}
