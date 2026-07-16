package jp.kitchen.Smart_Kitchen_Logistics_v2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "household_expenses")
public class HouseholdExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate date;

    protected HouseholdExpense() {}

    public HouseholdExpense(String category, double amount, LocalDate date) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("費目は必須です");
        }
        if (amount < 0) throw new IllegalArgumentException("金額は0以上");

        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public Long getId()         { return id; }
    public String getCategory() { return category; }
    public double getAmount()   { return amount; }
    public LocalDate getDate()  { return date; }
}
