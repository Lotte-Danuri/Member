package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.follow.dto.FollowDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping()
    @ApiOperation(value = "팔로우 등록", notes = "스토어 팔로우 등록")
    public ResponseEntity<?> register(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.register(dto), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "팔로우 조회", notes = "사용자가 팔로우한 스토어 목록 조회")
    public ResponseEntity<?> getStores(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.getStoresByFollow(dto.getMemberId()), HttpStatus.OK);
    }

    @PatchMapping()
    @ApiOperation(value = "팔로우 취소", notes = "스토어 팔로우 취소")
    public ResponseEntity<?> delete(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.delete(dto), HttpStatus.OK);
    }

    @GetMapping("/members")
    @ApiOperation(value = "팔로우 조회", notes = "스토어의 팔로우한 회원 목록 조회")
    public ResponseEntity<?> getMembers(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.getMembersByFollow(dto.getStoreId()), HttpStatus.OK);
    }
}
