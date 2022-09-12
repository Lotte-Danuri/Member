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

        String result = storeService.register(dto);

        assertThat(result).isEqualTo("스토어 등록이 완료되었습니다!");
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

        String result = storeService.register(dto);

        assertThat(result).isEqualTo("스토어 이름이 중복됩니다. 다른 이름을 사용하세요.");
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

        String result = storeService.update(dto);
        StoreDto store = storeService.getStore(1);

        assertThat(result).isEqualTo("수정 완료되었습니다!");
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

        String result = storeService.update(dto);

        assertThat(result).isEqualTo("존재하지 않는 스토어 입니다.");

    }

    @Test
    void 스토어_삭제_성공() {

        long storeId = 10;

        String result = storeService.delete(storeId);

        assertThat(result).isEqualTo("스토어 삭제 완료되었습니다.");
    }

    @Test
    void 스토어_삭제_실패() {

        long storeId = 20;

        String result = storeService.delete(storeId);

        assertThat(result).isEqualTo("존재하지 않거나 이미 삭제된 스토어입니다.");
    }
}
