package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.model.WeeklyMedal;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.WeeklyMedalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Component
public class DataInitializer implements CommandLineRunner {

    private final HouseholdExpenseRepository expenseRepository;
    private final WeeklyMedalRepository medalRepository;

    public DataInitializer(HouseholdExpenseRepository expenseRepository,
                           WeeklyMedalRepository medalRepository) {
        this.expenseRepository = expenseRepository;
        this.medalRepository = medalRepository;
    }

    @Override
    public void run(String... args) {
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);

        expenseRepository.save(new HouseholdExpense("食費",   45000, firstOfMonth));
        expenseRepository.save(new HouseholdExpense("光熱費", 12000, firstOfMonth.plusDays(3)));
        expenseRepository.save(new HouseholdExpense("通信費",  8000, firstOfMonth.plusDays(5)));
        expenseRepository.save(new HouseholdExpense("娯楽費", 20000, firstOfMonth.plusDays(7)));
        expenseRepository.save(new HouseholdExpense("食費",    3000, firstOfMonth.plusDays(10)));

        LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        if (medalRepository.findByWeekStartDate(weekStart).isEmpty()) {
            medalRepository.save(new WeeklyMedal(weekStart, today));
        }
    }
}
