package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.follow.dto.FollowDto;
import com.lotte.danuri.member.follow.dto.FollowRespDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping()
    @ApiOperation(value = "팔로우 등록", notes = "스토어 팔로우 등록")
    public ResponseEntity<?> register(@RequestHeader String memberId,
                                        @RequestBody FollowDto dto) {
        Long id = Long.parseLong(memberId);
        return new ResponseEntity<>(followService.register(id, dto.getStoreId()), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "팔로우 조회", notes = "사용자가 팔로우한 스토어 목록 조회")
    public ResponseEntity<?> getStores(@RequestHeader String memberId) {
        return new ResponseEntity<>(followService.getStoresByFollow(Long.parseLong(memberId)), HttpStatus.OK);
    }

    @PatchMapping("")
    @ApiOperation(value = "팔로우 취소", notes = "스토어 팔로우 취소")
    public ResponseEntity<?> delete(@RequestHeader String memberId,
                                    @RequestBody FollowDto dto) {
        Long id = Long.parseLong(memberId);
        return new ResponseEntity<>(followService.delete(id, dto), HttpStatus.OK);
    }

    @GetMapping("/members")
    @ApiOperation(value = "팔로우 조회", notes = "스토어의 팔로우한 회원 목록 조회")
    public ResponseEntity<?> getMembers(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.getMembersByFollow(dto.getStoreId()), HttpStatus.OK);
    }

    @GetMapping("/member/{storeId}")
    @ApiOperation(value = "회원의 팔로우 조회", notes = "회원의 스토어 팔로우 여부를 조회")
    public ResponseEntity<?> check(@RequestHeader String memberId,
                                    @PathVariable Long storeId) {
        FollowRespDto result = followService.check(Long.parseLong(memberId), storeId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
