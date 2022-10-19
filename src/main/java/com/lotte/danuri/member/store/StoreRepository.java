package com.lotte.danuri.member.store;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<List<Store>> findByIdIn(List<Long> storeIds);

    @Query(
        value = "SELECT distinct s FROM Store s JOIN FETCH s.followList f "
            + "WHERE s.id = :storeId "
            + "AND f.member.id = :memberId "
            + "AND s.deletedDate is null "
            + "AND f.deletedDate is null "
    )
    Optional<Store> findByFollow(Long storeId);

    Optional<Store> findByIdAndDeletedDateIsNull(Long id);

    Optional<List<Store>> findByBrandIdAndDeletedDateIsNull(Long brandId);

}
