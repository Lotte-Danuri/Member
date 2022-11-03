package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.likes.dto.LikesReqDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/likes")
@CrossOrigin(origins = "*")
public class LikesController {

    private final LikesService likesService;

    @PostMapping()
    @ApiOperation(value = "좋아요 등록", notes = "상품 좋아요 등록(추가)")
    public ResponseEntity<?> register(@RequestHeader String memberId,
                                        @RequestBody LikesReqDto dto) {
        return new ResponseEntity<>(likesService.register(Long.parseLong(memberId), dto.getProductCode()), HttpStatus.CREATED);
    }

    @PostMapping("/check")
    @ApiOperation(value = "좋아요 체크", notes = "좋아요한 상품인지 확인 조회")
    public ResponseEntity<?> check(@RequestHeader String memberId,
                                    @RequestBody LikesReqDto dto) {
        boolean result = likesService.checkLike(Long.parseLong(memberId), dto.getProductCode());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "좋아요 취소", notes = "상품 좋아요 삭제(취소)")
    public ResponseEntity<?> delete(@RequestHeader String memberId,
                                    @RequestBody LikesReqDto dto) {
        int result = likesService.delete(Long.parseLong(memberId), dto.getProductCode());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
