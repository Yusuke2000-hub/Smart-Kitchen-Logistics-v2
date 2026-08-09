package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.dto.DashboardViewModel;
import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.service.LivingCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class DashboardController {

    private static final double MONTHLY_INCOME = 200_000.0;
    private static final double TARGET_RATE = 70.0;

    private final LivingCostService livingCostService;
    private final HouseholdExpenseRepository expenseRepository;

    public DashboardController(LivingCostService livingCostService,
                               HouseholdExpenseRepository expenseRepository) {
        this.livingCostService = livingCostService;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/dashboard")
public String dashboard(Model model,
                         @RequestParam(required = false) String registered) {
    List<HouseholdExpense> expenses = expenseRepository.findAll();
    double defenseRate = livingCostService.calcDefenseRate(expenses, MONTHLY_INCOME);
    double totalAmount = expenses.stream().mapToDouble(HouseholdExpense::getAmount).sum();
    double gap = TARGET_RATE - defenseRate;

    model.addAttribute("vm", new DashboardViewModel(defenseRate, TARGET_RATE, gap, expenses, totalAmount));
    model.addAttribute("registered", registered);
    return "dashboard";
}
}
