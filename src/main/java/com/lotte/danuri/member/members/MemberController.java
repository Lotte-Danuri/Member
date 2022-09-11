package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    @PatchMapping("/info")
    public ResponseEntity<?> updateInfo(@RequestBody MemberInfoReqDto dto) {
        return new ResponseEntity<>(memberService.updateMemberInfo(dto), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getPurchases() {

        // Order 마이크로 서비스 사용

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/detail")
    public ResponseEntity<?> getDetailOfPurchases() {

        // Order 마이크로 서비스 사용

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrdersByStatus() {

        // Order 마이크로 서비스 사용 - 주문 상태 조회

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/likes")
    public ResponseEntity<?> getLikes(@RequestBody long memberId) {

        // product 마이크로 서비스에서 상품 목록 조회

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
