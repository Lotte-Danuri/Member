package com.lotte.danuri.member.store;

import static org.assertj.core.api.Assertions.assertThat;

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
            .sellerId(2)
            .name("aaaaaaaaaaaaa")
            .address("서울특별시 중구")
            .description("aaaaaaaa")
            .ownerName("aaa")
            .ownerNumber("010-1133-1133")
            .score(0)
            .build();

        int result = storeService.register(dto);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 스토어_등록_실패() {

        StoreDto dto = StoreDto.builder()
            .sellerId(2)
            .name("adidas")
            .address("경기도 김포시")
            .description("아디다스 매장")
            .ownerName("문인태")
            .ownerNumber("010-1122-1122")
            .score(0)
            .build();

        int result = storeService.register(dto);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void 스토어_등록_실패_셀러권한없는경우() {

        StoreDto dto = StoreDto.builder()
            .sellerId(1)
            .name("bbbb")
            .address("서울특별시 홍대")
            .description("bbbbbb")
            .ownerName("bbb")
            .ownerNumber("010-9999-2222")
            .score(0)
            .build();

        int result = storeService.register(dto);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void 스토어_조회() {

        long sellerId = 1;

        StoreDto storeDto = storeService.getStore(sellerId);

        assertThat(storeDto.getName()).isEqualTo("hello nikes~!~!~!~~!!~!!");

    }

    @Test
    void 스토어_정보_수정_성공() {

        StoreDto dto = StoreDto.builder()
            .id(10)
            .sellerId(1)
            .name("hello nikes~!~!~!~~!!~!!")
            .address("서울특별시 강남구")
            .description("~~~!~!~!~!~!~!~!~!")
            .ownerName("안채영")
            .ownerNumber("010-1111-1111")
            .build();

        int result = storeService.update(dto);
        StoreDto store = storeService.getStore(1);

        assertThat(result).isEqualTo(1);
        assertThat(store.getName()).isEqualTo("hello nikes~!~!~!~~!!~!!");

    }

    @Test
    void 스토어_정보_수정_실패() {

        StoreDto dto = StoreDto.builder()
            .sellerId(3)
            .name("hello nikes!!!!")
            .address("서울특별시 강남구")
            .description("~~~!~!~!~!~!~!~!~!")
            .ownerName("안채영")
            .ownerNumber("010-1111-1111")
            .build();

        int result = storeService.update(dto);

        assertThat(result).isEqualTo(-1);

    }

    @Test
    void 스토어_삭제_성공() {

        long storeId = 10;

        int result = storeService.delete(storeId);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 스토어_삭제_실패() {

        long storeId = 20;

        int result = storeService.delete(storeId);

        assertThat(result).isEqualTo(0);
    }
}
