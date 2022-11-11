package com.lotte.danuri.member.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.common.exception.exceptions.DuplicatedStoreNameException;
import com.lotte.danuri.member.common.exception.exceptions.NoAuthorizationException;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.store.dto.BrandDto;
import com.lotte.danuri.member.store.dto.StoreDto;
import com.lotte.danuri.member.store.dto.StoreInfoRespDto;
import com.lotte.danuri.member.store.dto.StoreRespDto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class StoreServiceTest {

    @Autowired
    StoreService storeService;

    @Test
    void 스토어_조회() {

        Long brandId = 6L;

        List<StoreRespDto> list = storeService.getStores(brandId);
        list.forEach(s -> {
            System.out.println(s.getStoreId() + " " + s.getStoreName());
        });

        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }

    @Test
    void 스토어_삭제_성공() {

        Long storeId = 10L;

        int result = storeService.delete(storeId);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 스토어_삭제_실패() {

        Long storeId = 100L;

        assertThatThrownBy(() -> storeService.delete(storeId))
            .isInstanceOf(NoStoreException.class)
            .hasMessageContaining("Store is not existed");
    }

    @Test
    void 스토어_이름_조회() {

        Long storeId = 1L;

        StoreInfoRespDto dto = storeService.getStoreInfo(storeId);

        assertThat(dto.getStoreName()).isEqualTo("강남점");

    }

    @Test
    void 스토어_아이디_조회_브랜드아이디로() {

        Long brandId = 1L;

        List<Long> stores = storeService.getStoreId(brandId);

        stores.forEach(System.out::println);
        assertThat(stores.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 브랜드_정보_조회() {

        BrandDto dto = storeService.getBrandInfo(9L);

        System.out.println(dto.getId() + " " + dto.getName() + " " + dto.getImageUrl());
        assertThat(dto.getId()).isEqualTo(9L);
    }
}
