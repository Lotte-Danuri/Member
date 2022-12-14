package com.lotte.danuri.member.follow;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<List<Follow>> findByStoreId(Long storeId);

    Optional<Follow> findByMemberIdAndStoreIdAndDeletedDateIsNull(Long memberId, Long storeId);

    Optional<List<Follow>> findByMemberIdAndDeletedDateIsNull(Long memberId);

}
