package com.lotte.danuri.member.cart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartRespDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
import com.lotte.danuri.member.client.dto.CartListRespDto;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    void 장바구니_상품_조회() {
        Long memberId = 1L;

        List<CartListRespDto> result = cartService.getProductsOfCart(memberId);

        result.forEach(c -> {
            System.out.println(c.getBrandName() + " " + c.getStoreName());
        });

        assertThat(result.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 장바구니_상품_수량_수정_성공() {

        CartUpdateReqDto dto = CartUpdateReqDto.builder().id(1L).quantity(100).build();

        int result = cartService.update(dto);

        assertThat(result).isEqualTo(1);
    }


    @Test
    void 장바구니_상품_수량_수정_실패() {

        CartUpdateReqDto dto = CartUpdateReqDto.builder().id(10L).build();

        assertThatThrownBy(() -> cartService.update(dto))
            .isInstanceOf(NoResourceException.class)
            .hasMessageContaining("Resource Not Exists");
    }

    @Test
    void 장바구니_상품_삭제_성공() {

        CartDeleteReqDto dto = CartDeleteReqDto.builder().id(1L).build();

        int result = cartService.delete(dto);

        assertThat(result).isEqualTo(1);
    }
}
