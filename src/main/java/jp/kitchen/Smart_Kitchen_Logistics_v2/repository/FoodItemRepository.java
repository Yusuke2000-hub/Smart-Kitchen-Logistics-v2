package jp.kitchen.Smart_Kitchen_Logistics_v2.repository;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}