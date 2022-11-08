package com.lotte.danuri.member.promotion;

import com.lotte.danuri.member.promotion.dto.PromotionListRespDto;
import com.lotte.danuri.member.promotion.dto.PromotionReqDto;
import com.lotte.danuri.member.promotion.dto.PromotionRespDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public PromotionRespDto getPromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findByIdAndDeletedDateIsNull(promotionId).orElseThrow();

        return PromotionRespDto.builder()
            .title(promotion.getTitle())
            .imageUrl(promotion.getImageUrl())
            .startDate(promotion.getStartDate())
            .endDate(promotion.getEndDate())
            .buttonUrl(promotion.getButtonUrl())
            .build();
    }

    @Override
    public List<PromotionListRespDto> getPromotions() {
        List<Promotion> promotionList = promotionRepository.findAllByDeletedDateIsNull().orElseGet(ArrayList::new);

        return promotionList.stream().map(p -> PromotionListRespDto.builder()
            .id(p.getId())
            .title(p.getTitle())
            .startDate(p.getStartDate())
            .endDate(p.getEndDate())
            .build()).collect(Collectors.toList());

    }

    @Override
    public List<PromotionListRespDto> getPromotionsByTime(PromotionReqDto dto) {
        List<Promotion> promotionList = promotionRepository
            .findAllByStartDateBetweenAndDeletedDateIsNull(dto.getStartDate(), dto.getEndDate()).orElseGet(ArrayList::new);

        return promotionList.stream().map(p -> PromotionListRespDto.builder()
            .id(p.getId())
            .title(p.getTitle())
            .startDate(p.getStartDate())
            .endDate(p.getEndDate())
            .build()).collect(Collectors.toList());
    }
}
