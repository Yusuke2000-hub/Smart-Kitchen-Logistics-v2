package jp.kitchen.Smart_Kitchen_Logistics_v2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weekly_medals")
public class WeeklyMedal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate weekStartDate;

    @Column(nullable = false)
    private LocalDate earnedDate;

    protected WeeklyMedal() {}

    public WeeklyMedal(LocalDate weekStartDate, LocalDate earnedDate) {
        if (weekStartDate == null) {
            throw new IllegalArgumentException("週開始日は必須です");
        }
        if (earnedDate == null) {
            throw new IllegalArgumentException("獲得日は必須です");
        }
        this.weekStartDate = weekStartDate;
        this.earnedDate = earnedDate;
    }

    public Long getId()               { return id; }
    public LocalDate getWeekStartDate() { return weekStartDate; }
    public LocalDate getEarnedDate()    { return earnedDate; }
}
