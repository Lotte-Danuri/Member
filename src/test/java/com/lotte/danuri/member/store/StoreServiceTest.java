package com.lotte.danuri.member.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.lotte.danuri.member.common.exception.exceptions.DuplicatedStoreNameException;
import com.lotte.danuri.member.common.exception.exceptions.NoAuthorizationException;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.store.dto.StoreDto;
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
    void 스토어_등록_성공() {

        StoreDto dto = StoreDto.builder()
            .sellerId(2L)
            .name("aaaaaaaaaaaaa")
            .address("서울특별시 중구")
            .description("aaaaaaaa")
            .ownerName("aaa")
            .ownerNumber("010-1133-1133")
            .score(0.0)
            .build();

        int result = storeService.register(dto);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 스토어_등록_실패() {

        StoreDto dto = StoreDto.builder()
            .sellerId(2L)
            .name("adidas")
            .address("경기도 김포시")
            .description("아디다스 매장")
            .ownerName("문인태")
            .ownerNumber("010-1122-1122")
            .score(0.0)
            .build();

        assertThatThrownBy(() -> storeService.register(dto))
            .isInstanceOf(DuplicatedStoreNameException.class)
            .hasMessageContaining("Duplicated Store Name");
    }

    @Test
    void 스토어_등록_실패_셀러권한없는경우() {

        StoreDto dto = StoreDto.builder()
            .sellerId(1L)
            .name("bbbb")
            .address("서울특별시 홍대")
            .description("bbbbbb")
            .ownerName("bbb")
            .ownerNumber("010-9999-2222")
            .score(0.0)
            .build();

        assertThatThrownBy(() -> storeService.register(dto))
            .isInstanceOf(NoAuthorizationException.class)
            .hasMessageContaining("Seller is unauthorized");

    }

    @Test
    void 스토어_조회() {

        Long sellerId = 1L;

        StoreDto storeDto = storeService.getStore(sellerId);

        assertThat(storeDto.getName()).isEqualTo("hello nikes~!~!~!~~!!~!!");

    }

    @Test
    void 스토어_정보_수정_성공() {

        StoreDto dto = StoreDto.builder()
            .id(10L)
            .sellerId(1L)
            .name("hello nikes~!~!~!~~!!~!!")
            .address("서울특별시 강남구")
            .description("~~~!~!~!~!~!~!~!~!")
            .ownerName("안채영")
            .ownerNumber("010-1111-1111")
            .build();

        int result = storeService.update(dto);
        StoreDto store = storeService.getStore(1L);

        assertThat(result).isEqualTo(1);
        assertThat(store.getName()).isEqualTo("hello nikes~!~!~!~~!!~!!");

    }

    @Test
    void 스토어_정보_수정_실패() {

        StoreDto dto = StoreDto.builder()
            .id(100L)
            .sellerId(3L)
            .name("hello nikes!!!!")
            .address("서울특별시 강남구")
            .description("~~~!~!~!~!~!~!~!~!")
            .ownerName("안채영")
            .ownerNumber("010-1111-1111")
            .build();

        assertThatThrownBy(() -> storeService.update(dto))
            .isInstanceOf(NoStoreException.class)
            .hasMessageContaining("Store is not existed");

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
}
