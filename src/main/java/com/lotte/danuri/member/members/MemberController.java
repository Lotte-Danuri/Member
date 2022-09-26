package com.lotte.danuri.member.members;

import com.lotte.danuri.member.cart.CartService;
import com.lotte.danuri.member.coupon.MyCouponService;
import com.lotte.danuri.member.follow.FollowService;
import com.lotte.danuri.member.likes.Likes;
import com.lotte.danuri.member.likes.LikesService;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberReqDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final LikesService likesService;
    private final CartService cartService;
    private final MyCouponService myCouponService;
    private final FollowService followService;

    @PatchMapping("/info")
    @ApiOperation(value = "회원 수정", notes = "회원의 개인정보 수정")
    public ResponseEntity<?> updateInfo(@RequestBody MemberInfoReqDto dto) {
        return new ResponseEntity<>(memberService.updateMemberInfo(dto), HttpStatus.OK);
    }

    @GetMapping("/products")
    @ApiOperation(value = "구매 목록 조회", notes = "회원의 구매 목록 조회")
    public ResponseEntity<?> getPurchases() {

        // Order 마이크로 서비스 사용

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/detail")
    @ApiOperation(value = "상품 상세 조회", notes = "회원의 구매 목록의 상품 상세 조회")
    public ResponseEntity<?> getDetailOfPurchases() {

        // Order 마이크로 서비스 사용

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders")
    @ApiOperation(value = "주문 조회", notes = "상품 주문 내역 상태별 조회")
    public ResponseEntity<?> getOrdersByStatus() {

        // Order 마이크로 서비스 사용 - 주문 상태 조회

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/likes")
    @ApiOperation(value = "좋아요 상품 조회", notes = "회원의 좋아요한 상품 조회")
    public ResponseEntity<?> getLikes(@RequestBody LikesReqDto dto) {

        List<Long> productList = likesService.getLikes(dto);

        // product 마이크로 서비스에서 상품 목록 조회
        // in 조건절 써서 상품 아이디 리스트 보내면 상품 리스트 리턴받게

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PatchMapping("/deletes")
    @ApiOperation(value = "회원 삭제", notes = "탈퇴에 의한 회원 삭제")
    public ResponseEntity<?> delete(@RequestBody MemberReqDto dto) {

        Long memberId = dto.getMemberId();

        memberService.delete(memberId);
        cartService.deleteAllByMember(memberId);
        myCouponService.deleteAllByMember(memberId);
        followService.deleteAllByMember(memberId);
        likesService.deleteAllByMember(memberId);

        return new ResponseEntity<>("회원 탈퇴 완료", HttpStatus.OK);
    }


}
