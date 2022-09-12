package com.lotte.danuri.member.store;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByMemberId(long memberId);

    Optional<Store> findByName(String name);
}
