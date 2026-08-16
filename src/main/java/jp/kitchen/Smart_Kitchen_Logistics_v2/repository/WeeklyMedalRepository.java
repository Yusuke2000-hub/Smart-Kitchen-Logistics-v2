package jp.kitchen.Smart_Kitchen_Logistics_v2.repository;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.WeeklyMedal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeeklyMedalRepository extends JpaRepository<WeeklyMedal, Long> {

    Optional<WeeklyMedal> findByWeekStartDate(LocalDate weekStartDate);
}
