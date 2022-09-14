package com.lotte.danuri.member.cart;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartInsertReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartRespDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
import com.lotte.danuri.member.common.exception.codes.CommonErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<CartRespDto> getProductsOfCart(CartReqDto dto) {

        memberRepository.findById(dto.getMemberId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS));

        List<CartRespDto> resultList = new ArrayList<>();

        cartRepository.findByMemberId(dto.getMemberId()).orElseGet(ArrayList::new)
            .forEach(cart -> {
                CartRespDto respDto = CartRespDto.builder().productId(cart.getProductId())
                    .quantity(cart.getQuantity()).build();

                resultList.add(respDto);
            });


        return resultList;
    }

    @Override
    public int register(CartInsertReqDto dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        cartRepository.save(dto.toEntity(member));
        return 1;
    }

    @Override
    public int update(CartUpdateReqDto dto) {
        Cart cart = cartRepository.findById(dto.getId()).orElseThrow(
            () -> new NoResourceException(CommonErrorCode.RESOURCE_NOT_FOUND.getMessage(), CommonErrorCode.RESOURCE_NOT_FOUND)
        );
        cart.update(dto.getQuantity());
        return 1;
    }

    @Override
    public int delete(CartDeleteReqDto dto) {
        cartRepository.deleteById(dto.getId());
        return 1;
    }
}
