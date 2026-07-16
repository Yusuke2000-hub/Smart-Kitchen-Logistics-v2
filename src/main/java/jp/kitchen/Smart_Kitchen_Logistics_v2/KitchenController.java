package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.service.LivingCostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class KitchenController {

    private final LivingCostService livingCostService;
    private final HouseholdExpenseRepository expenseRepository;

    public KitchenController(LivingCostService livingCostService,
                             HouseholdExpenseRepository expenseRepository) {
        this.livingCostService = livingCostService;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "GENKA LIFE へようこそ";
    }

    @GetMapping("/analysis")
    public String analysis() {
        List<HouseholdExpense> expenses = List.of(
                new HouseholdExpense("食費", 45000, LocalDate.now()),
                new HouseholdExpense("光熱費", 12000, LocalDate.now()),
                new HouseholdExpense("通信費", 8000, LocalDate.now())
        );
        return livingCostService.buildReport(expenses, 200000);
    }

    @GetMapping("/expenses/save")
    public String saveExpenses() {
        expenseRepository.save(new HouseholdExpense("食費", 45000, LocalDate.now()));
        expenseRepository.save(new HouseholdExpense("光熱費", 12000, LocalDate.now()));
        expenseRepository.save(new HouseholdExpense("通信費", 8000, LocalDate.now()));
        return "支出データをDBに保存しました";
    }

    @GetMapping("/expenses")
    public List<HouseholdExpense> getExpenses() {
        return expenseRepository.findAll();
    }
}
