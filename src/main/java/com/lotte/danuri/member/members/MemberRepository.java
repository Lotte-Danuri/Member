package com.lotte.danuri.member.members;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // [관리자] 판매자 조회 - role:3, status: -1(탈퇴),0(대기중),1(권한부여완료)
    Optional<List<Member>> findByRoleAndStatus(int role, int status);
}
