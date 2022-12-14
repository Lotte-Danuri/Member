package com.lotte.danuri.member.members;

import com.lotte.danuri.member.cart.CartService;
import com.lotte.danuri.member.client.OrderClient;
import com.lotte.danuri.member.client.ProductClient;
import com.lotte.danuri.member.client.dto.OrderHeaderDto;
import com.lotte.danuri.member.client.dto.ProductDto;
import com.lotte.danuri.member.client.dto.ProductListByCodeDto;
import com.lotte.danuri.member.coupon.MyCouponService;
import com.lotte.danuri.member.follow.FollowService;
import com.lotte.danuri.member.likes.LikesService;
import com.lotte.danuri.member.members.dto.MemberInfoReqDto;
import com.lotte.danuri.member.members.dto.MemberReqDto;
import com.lotte.danuri.member.members.dto.SignUpByOAuthDto;
import com.lotte.danuri.member.members.dto.SignUpDto;
import com.lotte.danuri.member.store.StoreService;
import com.lotte.danuri.member.store.dto.BrandDto;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;
    private final LikesService likesService;
    private final CartService cartService;
    private final MyCouponService myCouponService;
    private final FollowService followService;
    private final ProductClient productClient;
    private final OrderClient orderClient;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final StoreService storeService;

    public MemberController(MemberService memberService, LikesService likesService,
        CartService cartService, MyCouponService myCouponService, FollowService followService,
        ProductClient productClient, OrderClient orderClient,
        CircuitBreakerFactory circuitBreakerFactory, StoreService storeService) {
        this.memberService = memberService;
        this.likesService = likesService;
        this.cartService = cartService;
        this.myCouponService = myCouponService;
        this.followService = followService;
        this.productClient = productClient;
        this.orderClient = orderClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.storeService = storeService;
    }

    @PostMapping("/members")
    @ApiOperation(value = "?????? ?????? ??????", notes = "?????? ?????? ??? ?????? ??????")
    public ResponseEntity<?> save(@RequestBody SignUpDto dto) {

        log.info("Before Retrieve [register] Method IN [Member-Service]");
        Long memberId = memberService.register(dto);
        log.info("After Retrieve [getNames] Method IN [Member-Service]");

        return new ResponseEntity<>(memberId, HttpStatus.CREATED);
    }

    @PostMapping("/members/oAuth")
    @ApiOperation(value = "OAuth ?????? ?????? ??????", notes = "OAuth??? ????????????")
    public ResponseEntity<?> saveByOAuth(@RequestBody SignUpByOAuthDto dto) {

        log.info("Before Retrieve [registerByOAuth] Method IN [Member-Service]");
        Long memberId = memberService.registerByOAuth(dto);
        log.info("After Retrieve [registerByOAuth] Method IN [Member-Service]");

        return new ResponseEntity<>(memberId, HttpStatus.CREATED);
    }

    @GetMapping("/members")
    @ApiOperation(value = "?????? ?????? ??????", notes = "?????? ?????? ??? ?????? ??????")
    public ResponseEntity<?> getName(@RequestHeader String memberId) {
        return new ResponseEntity<>(memberService.getMember(Long.parseLong(memberId)), HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "?????? ?????? ??????", notes = "?????? ?????? ??? ?????? ??????")
    public ResponseEntity<?> getInfoOfMember(@PathVariable Long memberId) {
        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    @PatchMapping("/seller")
    @ApiOperation(value = "?????? ?????? ?????? ??????", notes = "????????? ????????? ????????? ??????")
    public ResponseEntity<?> updateSeller(@RequestHeader String memberId,
                                            @RequestBody Long storeId) {
        memberService.updateSeller(Long.parseLong(memberId), storeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/seller")
    @ApiOperation(value = "?????? ?????? ?????? ??????", notes = "????????? ????????? ????????? ??????")
    public ResponseEntity<?> getSeller(@RequestBody Long memberId) {
        return new ResponseEntity<>(memberService.getSeller(memberId), HttpStatus.OK);
    }


    @PostMapping("/info")
    @ApiOperation(value = "?????? ??????", notes = "????????? ???????????? ??????")
    public ResponseEntity<?> updateInfo(@RequestHeader String memberId,
                                        @RequestBody MemberInfoReqDto dto) {
        return new ResponseEntity<>(memberService.updateMemberInfo(Long.parseLong(memberId), dto), HttpStatus.OK);
    }

    @GetMapping("/products")
    @ApiOperation(value = "?????? ?????? ??????", notes = "????????? ?????? ?????? ??????")
    public ResponseEntity<?> getPurchases(@RequestHeader String memberId) {

        log.info("Before Call [getOrders] Method IN [Order-Service]");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        // Order ???????????? ????????? ??????
        List<OrderHeaderDto> resultList = circuitBreaker.run(() ->
            orderClient.getOrders(OrderHeaderDto.builder().buyerId(memberId).build()),
            throwable -> new ArrayList<>());
        log.info("After Call [getOrders] Method IN [Order-Service]");

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/products/detail")
    @ApiOperation(value = "?????? ?????? ??????", notes = "????????? ?????? ????????? ?????? ?????? ??????")
    public ResponseEntity<?> getDetailOfPurchases() {

        // Order ???????????? ????????? ??????

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders")
    @ApiOperation(value = "?????? ??????", notes = "?????? ?????? ?????? ????????? ??????")
    public ResponseEntity<?> getOrdersByStatus() {

        // Order ???????????? ????????? ?????? - ?????? ?????? ??????

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/like")
    @ApiOperation(value = "????????? ?????? ??????", notes = "????????? ???????????? ?????? ??????")
    public ResponseEntity<?> getLikes(@RequestHeader String memberId) {

        List<String> productList = likesService.getLikes(memberId);

        log.info("Before Call [getProducts] Method IN [Product-Service]");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        List<ProductDto> resultList = circuitBreaker.run(() ->
            productClient.getProductListByCode(ProductListByCodeDto.builder().productCode(productList).build()),
            throwable -> new ArrayList<>());
        log.info("After Call [getProducts] Method IN [Product-Service]");

        resultList.forEach(p -> {
            BrandDto brandDto = storeService.getBrand(p.getStoreId());
            p.update(brandDto.getName());
        });

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PatchMapping("/deletes")
    @ApiOperation(value = "?????? ??????", notes = "????????? ?????? ?????? ??????")
    public ResponseEntity<?> delete(@RequestBody MemberReqDto dto) {

        Long memberId = dto.getMemberId();

        memberService.delete(memberId);
        cartService.deleteAllByMember(memberId);
        myCouponService.deleteAllByMember(memberId);
        followService.deleteAllByMember(memberId);
        likesService.deleteAllByMember(memberId);

        return new ResponseEntity<>("?????? ?????? ??????", HttpStatus.OK);
    }

    @PatchMapping("/membership")
    @ApiOperation(value = "????????? ??????/????????????", notes = "???????????? ?????? ?????? ?????? ??????")
    public ResponseEntity<?> updateMembership(@RequestHeader(name = "memberId") String id) {

        log.info("Header memberId = {}", id);
        String memberId = "5"; // ???????????? ???????????? ????????????

        log.info("Before Call [getOrdersPrice] Method IN [Order-Service]");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        Long price = circuitBreaker.run(() ->
            orderClient.getOrdersPrice(OrderHeaderDto.builder().buyerId(memberId).build()),
            throwable -> 0L);
        log.info("After Call [getOrdersPrice] Method IN [Order-Service]");

        String rank;

        if(price == 0) {
            memberService.updateMemberShip(memberId, 1);
            rank = "??????";
        }else if(price >= 100000000) {
            memberService.updateMemberShip(memberId, 4);
            rank = "VVIP";
        }else if(price >= 50000000) {
            memberService.updateMemberShip(memberId, 3);
            rank = "SVIP";
        }else {
            memberService.updateMemberShip(memberId, 2);
            rank = "VIP";
        }

        return new ResponseEntity<>(rank, HttpStatus.OK);

    }
}
