package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ExpenseController {

    private final HouseholdExpenseRepository expenseRepository;

    public ExpenseController(HouseholdExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/expense/new")
    public String showExpenseForm() {
        return "expense-form";
    }

    @PostMapping("/expense/save")
    public String saveExpense(
            @RequestParam String category,
            @RequestParam double amount,
            @RequestParam String date
    ) {
        HouseholdExpense expense = new HouseholdExpense(category, amount, LocalDate.parse(date));
        expenseRepository.save(expense);

        return "redirect:/dashboard";
    }
}