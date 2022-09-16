package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.follow.dto.FollowDto;
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
    public ResponseEntity<?> register(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.register(dto), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getStores(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.getStoresByFollow(dto.getMemberId()), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<?> delete(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.delete(dto), HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity<?> getMembers(@RequestBody FollowDto dto) {
        return new ResponseEntity<>(followService.getMembersByFollow(dto.getStoreId()), HttpStatus.OK);
    }
}
