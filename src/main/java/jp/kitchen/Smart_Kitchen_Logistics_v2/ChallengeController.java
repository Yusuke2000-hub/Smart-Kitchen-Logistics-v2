package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.service.LivingCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChallengeController {

    private static final double MONTHLY_INCOME = 200_000.0;
    private static final double TARGET_RATE = 70.0;

    private final LivingCostService livingCostService;
    private final HouseholdExpenseRepository expenseRepository;

    public ChallengeController(LivingCostService livingCostService,
                               HouseholdExpenseRepository expenseRepository) {
        this.livingCostService = livingCostService;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/challenge")
    public String challenge(Model model) {
        List<HouseholdExpense> expenses = expenseRepository.findAll();
        double defenseRate = livingCostService.calcDefenseRate(expenses, MONTHLY_INCOME);
        boolean achieved = defenseRate <= TARGET_RATE;

        model.addAttribute("defenseRate", defenseRate);
        model.addAttribute("targetRate", TARGET_RATE);
        model.addAttribute("achieved", achieved);
        return "challenge";
    }
}
