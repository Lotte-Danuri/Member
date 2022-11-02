package com.lotte.danuri.member.likes;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<List<Likes>> findByMemberId(Long memberId);

    void deleteAllByMemberId(Long memberId);

    Optional<Likes> findByMemberIdAndProductCode(Long memberId, String productCode);

    void deleteByMemberIdAndProductCode(Long memberId, String productCode);
}
