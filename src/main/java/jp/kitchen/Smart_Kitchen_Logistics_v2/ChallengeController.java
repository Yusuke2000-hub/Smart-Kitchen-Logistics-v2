package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.HouseholdExpense;
import jp.kitchen.Smart_Kitchen_Logistics_v2.model.WeeklyMedal;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.HouseholdExpenseRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.repository.WeeklyMedalRepository;
import jp.kitchen.Smart_Kitchen_Logistics_v2.service.LivingCostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Controller
public class ChallengeController {

   private static final double MONTHLY_INCOME = 200_000.0;
    private static final double TARGET_RATE = 70.0;

    private final LivingCostService livingCostService;
    private final HouseholdExpenseRepository expenseRepository;
    private final WeeklyMedalRepository weeklyMedalRepository;

    public ChallengeController(LivingCostService livingCostService,
                               HouseholdExpenseRepository expenseRepository,
                               WeeklyMedalRepository weeklyMedalRepository) {
        this.livingCostService = livingCostService;
        this.expenseRepository = expenseRepository;
        this.weeklyMedalRepository = weeklyMedalRepository;
    }

    @GetMapping("/challenge")
    public String challenge(Model model, HttpSession session) {
        String coachType = (String) session.getAttribute("coachType");
        if (coachType == null) {
            coachType = "hotblood";
        }
        List<HouseholdExpense> expenses = expenseRepository.findAll();
        double defenseRate = livingCostService.calcDefenseRate(expenses, MONTHLY_INCOME);
        boolean achieved = defenseRate <= TARGET_RATE;

        LocalDate weekStart = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        if (achieved) {
            Optional<WeeklyMedal> existing = weeklyMedalRepository.findByWeekStartDate(weekStart);
            if (existing.isEmpty()) {
                weeklyMedalRepository.save(new WeeklyMedal(weekStart, LocalDate.now()));
            }
        }
        long totalMedals = weeklyMedalRepository.count();

        int evolutionStage;
        if (totalMedals >= 6) {
            evolutionStage = 3;
        } else if (totalMedals >= 3) {
            evolutionStage = 2;
        } else {
            evolutionStage = 1;
        }

        model.addAttribute("defenseRate", defenseRate);
        model.addAttribute("targetRate", TARGET_RATE);
        model.addAttribute("achieved", achieved);
        model.addAttribute("coachType", coachType);
        model.addAttribute("totalMedals", totalMedals);
        model.addAttribute("evolutionStage", evolutionStage);
        return "challenge";
    }

    @PostMapping("/challenge/coach")
    public String selectCoach(@RequestParam String type, HttpSession session) {
        session.setAttribute("coachType", type);
        return "redirect:/challenge";
    }
}
