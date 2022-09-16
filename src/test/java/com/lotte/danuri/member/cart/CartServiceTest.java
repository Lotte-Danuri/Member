package com.lotte.danuri.member.cart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartRespDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
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
    void 장바구니_상품_조회_성공() {

        CartReqDto dto = CartReqDto.builder().memberId(4L).build();

        List<CartRespDto> resultList = cartService.getProductsOfCart(dto);

        System.out.println(resultList.size());
        assertThat(resultList.size()).isGreaterThanOrEqualTo(0);

    }

    @Test
    void 장바구니_상품_조회_실패() {

        CartReqDto dto = CartReqDto.builder().memberId(100L).build();

        assertThatThrownBy(() -> cartService.getProductsOfCart(dto))
            .isInstanceOf(NoMemberException.class)
            .hasMessageContaining("Member is not Existed");
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
