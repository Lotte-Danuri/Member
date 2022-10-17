package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.List;

public interface StoreService {

    int register(StoreDto dto);

    StoreDto getStore(Long sellerId);

    int update(StoreDto dto);

    int updateImage(StoreDto dto);

    int delete(Long storeId);

    String getName(Long storeId);

    List<Long> getStoreId(Long brandId);

}
