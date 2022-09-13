package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.likes.dto.LikesDeleteReqDto;
import com.lotte.danuri.member.likes.dto.LikesInsertReqDto;
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
    public ResponseEntity<?> register(@RequestBody LikesInsertReqDto dto) {
        return new ResponseEntity<>(likesService.register(dto), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody LikesDeleteReqDto dto) {
        return new ResponseEntity<>(likesService.delete(dto), HttpStatus.OK);
    }
}
