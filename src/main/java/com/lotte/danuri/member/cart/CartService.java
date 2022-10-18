package com.lotte.danuri.member.cart;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartInsertReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartRespDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
import com.lotte.danuri.member.client.dto.CartListRespDto;
import java.util.List;

public interface CartService {

    List<CartListRespDto> getProductsOfCart(Long memberId);

    int register(CartInsertReqDto dto);

    int update(CartUpdateReqDto dto);

    int delete(CartDeleteReqDto dto);

    int deleteAllByMember(Long memberId);

}
