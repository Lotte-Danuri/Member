package com.lotte.danuri.member.store;

import com.lotte.danuri.member.common.exception.codes.CommonErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.DuplicatedStoreNameException;
import com.lotte.danuri.member.common.exception.exceptions.NoAuthorizationException;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.dto.BrandDto;
import com.lotte.danuri.member.store.dto.StoreDto;
import com.lotte.danuri.member.store.dto.StoreInfoRespDto;
import com.lotte.danuri.member.store.dto.StoreRespDto;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final BrandRepository brandRepository;

    @Override
    public BrandDto getBrand(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS)
        );

        Brand brand = brandRepository.findById(store.getBrand().getId()).orElseThrow(
            () -> new NoResourceException(CommonErrorCode.RESOURCE_NOT_FOUND.getMessage(), CommonErrorCode.RESOURCE_NOT_FOUND)
        );

        return BrandDto.builder()
            .id(brand.getId())
            .name(brand.getName())
            .imageUrl(brand.getImage())
            .build();
    }

    @Override
    public List<StoreRespDto> getStores(Long brandId) {

        List<Store> storeList =
            storeRepository.findByBrandIdAndDeletedDateIsNull(brandId).orElseGet(ArrayList::new);

        return storeList.stream().map(s -> StoreRespDto.builder()
                                            .storeId(s.getId())
                                            .storeName(s.getName())
                                            .build()).collect(Collectors.toList());

    }

    @Override
    public int update(StoreDto dto) {

        memberRepository.findByIdAndDeletedDateIsNull(dto.getSellerId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        Optional<Store> findStore = storeRepository.findById(dto.getId());

        if(findStore.isEmpty()) {
            throw new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS);
        }else {
            Store store = findStore.get();
            store.update(dto);
            return 1;
        }

    }

    @Override
    public int delete(Long storeId) {

        if(storeRepository.findById(storeId).isEmpty()) {
            throw new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS);
        }else {
            Store store = storeRepository.findById(storeId).get();
            store.delete();
            return 1;
        }
    }

    @Override
    public StoreInfoRespDto getStoreInfo(Long storeId) {
        Store store = storeRepository.findByIdAndDeletedDateIsNull(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(),
                StoreErrorCode.NO_STORE_EXISTS)
        );

        return StoreInfoRespDto.builder()
            .storeId(store.getId())
            .storeName(store.getName())
            .brandId(store.getBrand().getId())
            .brandName(store.getBrand().getName())
            .build();
    }

    @Override
    public List<Long> getStoreId(Long brandId) {
        List<Store> stores = storeRepository.findByBrandIdAndDeletedDateIsNull(brandId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(),
                StoreErrorCode.NO_STORE_EXISTS)
        );

        return stores.stream().map(BaseEntity::getId).collect(Collectors.toList());

    }

    @Override
    public BrandDto getBrandInfo(Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(
            () -> new NoSuchElementException(CommonErrorCode.RESOURCE_NOT_FOUND.getMessage())
        );

        return BrandDto.builder()
            .id(brand.getId())
            .name(brand.getName())
            .imageUrl(brand.getImage())
            .build();
    }
}
