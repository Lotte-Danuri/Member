package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;
import com.lotte.danuri.member.store.dto.StoreRespDto;
import java.util.List;

public interface StoreService {

    List<StoreRespDto> getStores(Long storeId);

    int update(StoreDto dto);

    int updateImage(StoreDto dto);

    int delete(Long storeId);

    String getName(Long storeId);

    List<Long> getStoreId(Long brandId);

}
