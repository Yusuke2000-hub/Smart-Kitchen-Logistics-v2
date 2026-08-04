package jp.kitchen.Smart_Kitchen_Logistics_v2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseController {

    @GetMapping("/expense/new")
    public String showExpenseForm() {
        return "expense-form";
    }
}