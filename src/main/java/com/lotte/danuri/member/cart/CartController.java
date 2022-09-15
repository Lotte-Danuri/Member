package com.lotte.danuri.member.cart;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartInsertReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public ResponseEntity<?> getProductsOfCart(@RequestBody CartReqDto dto) {
        return new ResponseEntity<>(cartService.getProductsOfCart(dto), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody CartInsertReqDto dto) {
        return new ResponseEntity<>(cartService.register(dto), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<?> update(@RequestBody CartUpdateReqDto dto) {
        return new ResponseEntity<>(cartService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody CartDeleteReqDto dto) {
        return new ResponseEntity<>(cartService.delete(dto), HttpStatus.OK);
    }
}
