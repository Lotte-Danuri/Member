package com.lotte.danuri.member.cart;

import com.lotte.danuri.member.cart.dto.CartDeleteReqDto;
import com.lotte.danuri.member.cart.dto.CartInsertReqDto;
import com.lotte.danuri.member.cart.dto.CartReqDto;
import com.lotte.danuri.member.cart.dto.CartRespDto;
import com.lotte.danuri.member.cart.dto.CartUpdateReqDto;
import com.lotte.danuri.member.client.ProductClient;
import com.lotte.danuri.member.client.dto.CartListRespDto;
import com.lotte.danuri.member.client.dto.ProductDto;
import com.lotte.danuri.member.client.dto.ProductListDto;
import com.lotte.danuri.member.common.exception.codes.CommonErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.Store;
import com.lotte.danuri.member.store.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductClient productClient;
    private final StoreRepository storeRepository;

    @Override
    public List<CartListRespDto> getProductsOfCart(CartReqDto dto) {

        memberRepository.findByIdAndDeletedDateIsNull(dto.getMemberId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS));

        List<Cart> resultList =
            cartRepository.findByMemberId(dto.getMemberId()).orElseGet(ArrayList::new);

        List<Long> productIdList = resultList.stream().map(Cart::getProductId).toList();
        List<ProductDto> productList =
            productClient.getProducts(ProductListDto.builder().productId(productIdList).build());

        List<Long> storeIdList = productList.stream().map(ProductDto::getStoreId).toList();

        List<String> storeNameList = new ArrayList<>();
        storeIdList.forEach(id -> {
            String name = storeRepository.findById(id).get().getName();
            storeNameList.add(name);
        });

        List<CartListRespDto> cartList = new ArrayList<>();
        for(int i=0;i<resultList.size();i++) {
            cartList.add(CartListRespDto.builder()
                .quantity(resultList.get(i).getQuantity())
                .productDto(productList.get(i))
                .storeName(storeNameList.get(i))
                .build());
        }

        return cartList;

    }

    @Override
    public int register(CartInsertReqDto dto) {
        Member member = memberRepository.findByIdAndDeletedDateIsNull(dto.getMemberId()).orElseThrow(
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

    @Override
    public int deleteAllByMember(Long memberId) {
        cartRepository.deleteAllByMemberId(memberId);
        return 1;
    }
}
