package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @PatchMapping("/sellers")
    @ApiOperation(value = "셀러 권한 수정", notes = "셀러의 권한 대기중 -> 허가로 수정")
    public ResponseEntity<?> manageSellers(@RequestBody SellerAuthReqDto dto) {
        Long updatedMemberId = adminService.updateSellerAuth(dto);

        return new ResponseEntity<>(updatedMemberId, HttpStatus.OK);
    }

    @GetMapping("/sellers")
    @ApiOperation(value = "셀러 조회", notes = "권한 부여 대기중인 셀러 목록 조회")
    public ResponseEntity<?> getSellers(@RequestBody SellerAuthReqDto dto) {
        return new ResponseEntity<>(adminService.getSellers(dto), HttpStatus.OK);
    }

}
