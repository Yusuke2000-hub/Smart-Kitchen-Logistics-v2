package jp.kitchen.Smart_Kitchen_Logistics_v2.repository;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdExpenseRepository extends JpaRepository<HouseholdExpense, Long> {
}
