package jp.kitchen.Smart_Kitchen_Logistics_v2.dto;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;

import java.util.List;

public class DashboardViewModel {

    private final double defenseRate;
    private final double targetRate;
    private final double gap;
    private final List<HouseholdExpense> expenses;
    private final double totalAmount;
    private final double barWidth;

    public DashboardViewModel(double defenseRate, double targetRate, double gap,
                              List<HouseholdExpense> expenses, double totalAmount) {
        this.defenseRate = defenseRate;
        this.targetRate = targetRate;
        this.gap = gap;
        this.expenses = expenses;
        this.totalAmount = totalAmount;
        this.barWidth = Math.min(defenseRate, 100.0);
    }

    public double getDefenseRate() { return defenseRate; }
    public double getTargetRate()  { return targetRate; }
    public double getGap()         { return gap; }
    public List<HouseholdExpense> getExpenses() { return expenses; }
    public double getTotalAmount() { return totalAmount; }
    public double getBarWidth()    { return barWidth; }
}
