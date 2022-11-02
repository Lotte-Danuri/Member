package com.lotte.danuri.member.cart;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartInsertReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping()
    @ApiOperation(value = "상품 조회", notes = "장바구니에 담겨있는 상품 조회")
    public ResponseEntity<?> getProductsOfCart(@RequestHeader String memberId) {
        return new ResponseEntity<>(cartService.getProductsOfCart(Long.parseLong(memberId)), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "상품 등록", notes = "장바구니에 상품 추가")
    public ResponseEntity<?> register(@RequestHeader String memberId,
                                        @RequestBody CartInsertReqDto dto) {
        return new ResponseEntity<>(cartService.register(Long.parseLong(memberId), dto), HttpStatus.OK);
    }

    @PatchMapping()
    @ApiOperation(value = "상품 수량 수정", notes = "장바구니에 담긴 상품의 주문 수량을 수정")
    public ResponseEntity<?> update(@RequestBody CartUpdateReqDto dto) {
        return new ResponseEntity<>(cartService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "상품 삭제", notes = "장바구니의 상품 삭제")
    public ResponseEntity<?> delete(@RequestBody CartDeleteReqDto dto) {
        return new ResponseEntity<>(cartService.delete(dto), HttpStatus.OK);
    }
}
