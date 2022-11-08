package com.lotte.danuri.member.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import com.lotte.danuri.member.promotion.dto.PromotionListRespDto;
import com.lotte.danuri.member.promotion.dto.PromotionReqDto;
import com.lotte.danuri.member.promotion.dto.PromotionRespDto;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    PromotionService promotionService;

    @Test
    void 프로모션_조회() {
        Long id = 1L;

        PromotionRespDto dto = promotionService.getPromotion(id);

        System.out.println(dto.getTitle() + " " + dto.getImageUrl());
        assertThat(dto.getTitle()).isEqualTo("프로모션1");
    }

    @Test
    void 프로모션_전체_조회() {
        List<PromotionListRespDto> result = promotionService.getPromotions();

        result.forEach(PromotionListRespDto::getTitle);
        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    void 프로모션_기간별_조회() {

        PromotionReqDto dto = PromotionReqDto.builder()
            .startDate(LocalDateTime.MIN)
            .endDate(LocalDateTime.MAX)
            .build();

        List<PromotionListRespDto> result = promotionService.getPromotionsByTime(dto);

        result.forEach(PromotionListRespDto::getTitle);
        assertThat(result.size()).isGreaterThanOrEqualTo(0);
    }

}
