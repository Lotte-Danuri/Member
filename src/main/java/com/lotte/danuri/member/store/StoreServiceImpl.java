package com.lotte.danuri.member.store;

import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.DuplicatedStoreNameException;
import com.lotte.danuri.member.common.exception.exceptions.NoAuthorizationException;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.Optional;
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
    public int register(StoreDto dto) {

        if(storeRepository.findByNameAndDeletedDateIsNull(dto.getName()).isPresent()) {
            throw new DuplicatedStoreNameException(StoreErrorCode.DUPLICATED_STORE_NAME.getMessage(), StoreErrorCode.DUPLICATED_STORE_NAME);
        }else {
            Member findMember = memberRepository.findByIdAndDeletedDateIsNull(dto.getSellerId()).orElseThrow(
                () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
            );

            if(findMember.getStatus() == 0) {
                throw new NoAuthorizationException(MemberErrorCode.NO_AUTHORIZED_SELLER.getMessage(), MemberErrorCode.NO_AUTHORIZED_SELLER);
            }else {
                Store store = dto.toEntity(findMember);
                storeRepository.save(store);
                return 1;
            }

        }

    }

    @Override
    public StoreDto getStore(Long sellerId) {
        Store findStore = storeRepository.findByMemberIdAndDeletedDateIsNull(sellerId).orElseGet(Store::new);

        return findStore.toDto();
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
    public int updateImage(StoreDto dto) {

        Member findMember = memberRepository.findByIdAndDeletedDateIsNull(dto.getSellerId()).orElseThrow();

        if(storeRepository.findById(dto.getId()).isEmpty()) {
            throw new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS);
        }else {
            storeRepository.save(Store.builder().image(dto.getImage()).build());
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
}
