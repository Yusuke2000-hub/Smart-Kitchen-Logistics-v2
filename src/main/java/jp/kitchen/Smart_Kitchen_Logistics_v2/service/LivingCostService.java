package jp.kitchen.Smart_Kitchen_Logistics_v2.service;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivingCostService {

    private static final double TARGET_RATE = 70.0;

    public double calcDefenseRate(List<HouseholdExpense> expenses, double monthlyIncome) {
        if (expenses == null || expenses.isEmpty() || monthlyIncome <= 0) {
            return 0.0;
        }
        double totalExpenses = expenses.stream().mapToDouble(HouseholdExpense::getAmount).sum();
        return (totalExpenses / monthlyIncome) * 100;
    }

    public String buildReport(List<HouseholdExpense> expenses, double monthlyIncome) {
        if (expenses == null || expenses.isEmpty()) {
            return "支出データがありません";
        }

        double totalExpenses = expenses.stream().mapToDouble(HouseholdExpense::getAmount).sum();
        double defenseRate = calcDefenseRate(expenses, monthlyIncome);

        StringBuilder sb = new StringBuilder();
        sb.append("=== GENKA LIFE 生活防衛力レポート ===\n");
        sb.append(String.format("生活防衛力: %.1f%% (目標: %.1f%%)%n", defenseRate, TARGET_RATE));
        if (defenseRate > TARGET_RATE) {
            sb.append("[警告] 支出が目標比率を超過しています！\n");
        }
        expenses.forEach(e ->
            sb.append(String.format("費目: %-10s | 金額: %8.0f円 | 日付: %s%n",
                    e.getCategory(), e.getAmount(), e.getDate()))
        );
        sb.append(String.format("%n総支出: %.0f円%n", totalExpenses));
        return sb.toString();
    }
}
