package com.lotte.danuri.member.store;

import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public String register(StoreDto dto) {

        // 판매자 등록된 상태인지 확인하는 것도 추가할 것
        if(storeRepository.findByName(dto.getName()).isPresent()) {
            return "스토어 이름이 중복됩니다. 다른 이름을 사용하세요.";
        }else {
            Member findMember = memberRepository.findById(dto.getSellerId()).orElseThrow();
            Store store = dto.toEntity(findMember);

            Store savedStore = storeRepository.save(store);
            return "스토어 등록이 완료되었습니다!";
        }

    }

    @Override
    public StoreDto getStore(long sellerId) {
        Store findStore = storeRepository.findByMemberId(sellerId).orElseGet(Store::new);

        return findStore.toDto();
    }

    @Override
    public String update(StoreDto dto) {

        Member findMember = memberRepository.findById(dto.getSellerId()).orElseThrow();

        Optional<Store> findStore = storeRepository.findById(dto.getId());

        if(findStore.isEmpty()) {
            return "존재하지 않는 스토어 입니다.";
        }else {
            Store store = findStore.get();
            store.update(dto);
            storeRepository.save(store);
            return "수정 완료되었습니다!";
        }

    }

    @Override
    public String updateImage(StoreDto dto) {

        Member findMember = memberRepository.findById(dto.getSellerId()).orElseThrow();

        if(storeRepository.findById(dto.getId()).isEmpty()) {
            return "존재하지 않는 스토어 입니다.";
        }else {
            storeRepository.save(Store.builder().image(dto.getImage()).build());
            return "스토어 이미지 수정이 완료되었습니다.";
        }
    }

    @Override
    public String delete(long storeId) {

        if(storeRepository.findById(storeId).isEmpty()) {
            return "존재하지 않거나 이미 삭제된 스토어입니다.";
        }else {
            storeRepository.deleteById(storeId);
            return "스토어 삭제 완료되었습니다.";
        }
    }
}
