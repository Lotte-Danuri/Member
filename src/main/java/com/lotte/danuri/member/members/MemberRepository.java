package com.lotte.danuri.member.members;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // [관리자] 판매자 조회 - role:3, status: -1(탈퇴),0(대기중),1(권한부여완료)
    Optional<List<Member>> findByRoleAndStatus(int role, int status);

    @Query(
        value = "SELECT distinct m FROM Member m JOIN FETCH m.followList f "
            + "WHERE m.id = :memberId "
            + "AND f.member.id = :memberId "
            + "AND m.deletedDate is null "
            + "AND f.deletedDate is null "
    )
    Optional<Member> findByIdAndFollow(Long memberId);

    Optional<Member> findByIdAndDeletedDateIsNull(Long memberId);
}
