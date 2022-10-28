package com.lotte.danuri.member.store;

import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.DuplicatedStoreNameException;
import com.lotte.danuri.member.common.exception.exceptions.NoAuthorizationException;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.domain.BaseEntity;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.dto.StoreDto;
import com.lotte.danuri.member.store.dto.StoreRespDto;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<StoreRespDto> getStores(Long storeId) {

        Store store = storeRepository.findByIdAndDeletedDateIsNull(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(),
                                        StoreErrorCode.NO_STORE_EXISTS)
        );

        List<Store> storeList =
            storeRepository.findByBrandIdAndDeletedDateIsNull(store.getBrand().getId()).orElseGet(ArrayList::new);

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
    public String getName(Long storeId) {
        Store store = storeRepository.findByIdAndDeletedDateIsNull(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(),
                StoreErrorCode.NO_STORE_EXISTS)
        );

        return store.getName();
    }

    @Override
    public List<Long> getStoreId(Long brandId) {
        List<Store> stores = storeRepository.findByBrandIdAndDeletedDateIsNull(brandId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(),
                StoreErrorCode.NO_STORE_EXISTS)
        );

        return stores.stream().map(BaseEntity::getId).collect(Collectors.toList());

    }
}
