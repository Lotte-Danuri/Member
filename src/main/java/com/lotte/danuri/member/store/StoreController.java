package com.lotte.danuri.member.store;

import com.lotte.danuri.member.store.dto.StoreDto;
import com.lotte.danuri.member.store.dto.StoreRespDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/store")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{storeId}/list")
    @ApiOperation(value = "스토어 목록 조회", notes = "브랜드의 스토어(지점) 조회")
    public ResponseEntity<?> getStore(@PathVariable(name = "storeId") Long storeId) {

        List<StoreRespDto> storeList = storeService.getStores(storeId);

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }

    @PatchMapping()
    @ApiOperation(value = "스토어 수정", notes = "스토어 정보 수정")
    public ResponseEntity<?> update(@RequestBody StoreDto dto) {
        int result = storeService.update(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 이미지 수정 API 추후에 예정

    @PatchMapping("/delete")
    @ApiOperation(value = "스토어 삭제", notes = "스토어 삭제")
    public ResponseEntity<?> delete(@RequestBody StoreDto dto) {

        int result = storeService.delete(dto.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/name/{storeId}")
    @ApiOperation(value = "스토어 이름 조회", notes = "스토어 이름을 조회")
    public ResponseEntity<?> getNames(@PathVariable(name = "storeId") Long storeId) {

        log.info("Before Retrieve [getNames] Method IN [Member-Service]");
        String name = storeService.getName(storeId);
        log.info("After Retrieve [getNames] Method IN [Member-Service]");

        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    @ApiOperation(value = "스토어 id 조회", notes = "브랜드로 스토어 아이디 조회")
    public ResponseEntity<?> getStoreId(@PathVariable(name = "brandId") Long brandId) {

        log.info("Before Retrieve [getStoreId] Method IN [Member-Service]");
        List<Long> storeIds = storeService.getStoreId(brandId);
        log.info("After Retrieve [getStoreId] Method IN [Member-Service]");

        return new ResponseEntity<>(storeIds, HttpStatus.OK);
    }

}
