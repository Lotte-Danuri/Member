package com.lotte.danuri.member.store;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByMemberId(Long memberId);

    Optional<Store> findByName(String name);

    Optional<List<Store>> findByIdIn(List<Long> storeIds);

}
