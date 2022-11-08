package com.lotte.danuri.member.promotion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Optional<Promotion> findByIdAndDeletedDateIsNull(Long promotionId);

    Optional<List<Promotion>> findAllByDeletedDateIsNull();

    Optional<List<Promotion>> findAllByStartDateBetweenAndDeletedDateIsNull(LocalDateTime start, LocalDateTime end);
}
