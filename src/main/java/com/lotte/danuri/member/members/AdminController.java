package com.lotte.danuri.member.members;

import com.lotte.danuri.member.members.dto.SellerAuthReqDto;
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
    public ResponseEntity<?> manageSellers(@RequestBody SellerAuthReqDto dto) {
        Long updatedMemberId = adminService.updateSellerAuth(dto);

        return new ResponseEntity<>(updatedMemberId, HttpStatus.OK);
    }

    @GetMapping("/sellers")
    public ResponseEntity<?> getSellers(@RequestBody SellerAuthReqDto dto) {
        return new ResponseEntity<>(adminService.getSellers(dto), HttpStatus.OK);
    }

}
