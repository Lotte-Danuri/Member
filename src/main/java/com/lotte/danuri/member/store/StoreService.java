package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;

public interface StoreService {

    int register(StoreDto dto);

    StoreDto getStore(Long sellerId);

    int update(StoreDto dto);

    int updateImage(StoreDto dto);

    int delete(Long storeId);

}
