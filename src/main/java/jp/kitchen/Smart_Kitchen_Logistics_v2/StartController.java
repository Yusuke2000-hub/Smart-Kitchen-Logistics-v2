package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.service.LivingCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StartController {

    private static final double MONTHLY_INCOME = 200_000.0;

    private final LivingCostService livingCostService;
    private final HouseholdExpenseRepository expenseRepository;

    public StartController(LivingCostService livingCostService,
                           HouseholdExpenseRepository expenseRepository) {
        this.livingCostService = livingCostService;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/")
    public String start(Model model) {
        List<HouseholdExpense> expenses = expenseRepository.findAll();
        double defenseRate = livingCostService.calcDefenseRate(expenses, MONTHLY_INCOME);

        model.addAttribute("defenseRate", defenseRate);
        model.addAttribute("level", 1);
        return "start";
    }
}
