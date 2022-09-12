package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;

public interface StoreService {

    String register(StoreDto dto);

    StoreDto getStore(long sellerId);

    String update(StoreDto dto);

    String updateImage(StoreDto dto);

    String delete(long storeId);

}
