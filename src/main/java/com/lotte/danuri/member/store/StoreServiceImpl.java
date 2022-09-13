package com.lotte.danuri.member.store;

import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public int register(StoreDto dto) {

        if(storeRepository.findByName(dto.getName()).isPresent()) {
            return -1;
        }else {
            Member findMember = memberRepository.findById(dto.getSellerId()).orElseThrow();

            if(findMember.getStatus() == 0) {
                return 0;
            }else {
                Store store = dto.toEntity(findMember);
                storeRepository.save(store);
                return 1;
            }

        }

    }

    @Override
    public StoreDto getStore(long sellerId) {
        Store findStore = storeRepository.findByMemberId(sellerId).orElseGet(Store::new);

        return findStore.toDto();
    }

    @Override
    public int update(StoreDto dto) {

        if(memberRepository.findById(dto.getSellerId()).isEmpty()) {
            return -1;
        }

        Optional<Store> findStore = storeRepository.findById(dto.getId());

        if(findStore.isEmpty()) {
            return 0;
        }else {
            Store store = findStore.get();
            store.update(dto);
            storeRepository.save(store);
            return 1;
        }

    }

    @Override
    public int updateImage(StoreDto dto) {

        Member findMember = memberRepository.findById(dto.getSellerId()).orElseThrow();

        if(storeRepository.findById(dto.getId()).isEmpty()) {
            return 0;
        }else {
            storeRepository.save(Store.builder().image(dto.getImage()).build());
            return 1;
        }
    }

    @Override
    public int delete(long storeId) {

        if(storeRepository.findById(storeId).isEmpty()) {
            return 0;
        }else {
            storeRepository.deleteById(storeId);
            return 1;
        }
    }
}
