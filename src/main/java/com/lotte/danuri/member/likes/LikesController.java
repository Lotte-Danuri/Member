package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.likes.dto.LikesDeleteReqDto;
import com.lotte.danuri.member.likes.dto.LikesInsertReqDto;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping()
    @ApiOperation(value = "좋아요 등록", notes = "상품 좋아요 등록(추가)")
    public ResponseEntity<?> register(@RequestBody LikesReqDto dto) {
        return new ResponseEntity<>(likesService.register(dto), HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "좋아요 취소", notes = "상품 좋아요 삭제(취소)")
    public ResponseEntity<?> delete(@RequestBody LikesReqDto dto) {
        return new ResponseEntity<>(likesService.delete(dto), HttpStatus.OK);
    }
}
