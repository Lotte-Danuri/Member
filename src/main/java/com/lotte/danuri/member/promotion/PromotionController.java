package com.lotte.danuri.member.promotion;

import com.lotte.danuri.member.promotion.dto.PromotionListRespDto;
import com.lotte.danuri.member.promotion.dto.PromotionReqDto;
import com.lotte.danuri.member.promotion.dto.PromotionRespDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
@Slf4j
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/{promotionId}")
    @ApiOperation(value = "프로모션 상세 조회", notes = "프로모션을 상세 조회")
    public ResponseEntity<?> getStore(@PathVariable Long promotionId) {
        PromotionRespDto result = promotionService.getPromotion(promotionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "프로모션 전체 조회", notes = "프로모션 목록 전체 조회")
    public ResponseEntity<?> getAllStores() {
        List<PromotionListRespDto> result = promotionService.getPromotions();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/all/time")
    @ApiOperation(value = "프로모션 날짜별 조회", notes = "프로모션 날짜별 목록 조회")
    public ResponseEntity<?> getStoresByTime(@RequestBody PromotionReqDto dto) {
        List<PromotionListRespDto> result = promotionService.getPromotionsByTime(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
