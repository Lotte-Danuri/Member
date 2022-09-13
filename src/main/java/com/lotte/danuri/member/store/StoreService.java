package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;

public interface StoreService {

    int register(StoreDto dto);

    StoreDto getStore(long sellerId);

    int update(StoreDto dto);

    int updateImage(StoreDto dto);

    int delete(long storeId);

}
