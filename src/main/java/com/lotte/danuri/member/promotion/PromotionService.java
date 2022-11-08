package com.lotte.danuri.member.promotion;

import com.lotte.danuri.member.promotion.dto.PromotionListRespDto;
import com.lotte.danuri.member.promotion.dto.PromotionReqDto;
import com.lotte.danuri.member.promotion.dto.PromotionRespDto;
import java.util.List;

public interface PromotionService {

    PromotionRespDto getPromotion(Long promotionId);

    List<PromotionListRespDto> getPromotions();

    List<PromotionListRespDto> getPromotionsByTime(PromotionReqDto dto);

}
