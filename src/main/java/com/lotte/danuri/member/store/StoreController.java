package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody StoreDto dto) {
        int result = storeService.register(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getStore(@RequestBody StoreDto dto) {
        StoreDto respDto = storeService.getStore(dto.getSellerId());

        return new ResponseEntity<>(respDto, HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<?> update(@RequestBody StoreDto dto) {
        int result = storeService.update(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 이미지 수정 API 추후에 예정

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody StoreDto dto) {
        // 상태로 삭제 관리 추가하기
        int result = storeService.delete(dto.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
