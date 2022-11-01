package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.BrandDto;
import com.lotte.danuri.member.store.dto.StoreDto;
import com.lotte.danuri.member.store.dto.StoreRespDto;
import java.util.List;

public interface StoreService {

    BrandDto getBrand(Long storeId);

    List<StoreRespDto> getStores(Long storeId);

    int update(StoreDto dto);

    int delete(Long storeId);

    String getName(Long storeId);

    List<Long> getStoreId(Long brandId);

}
