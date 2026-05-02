package jp.kitchen.Smart_Kitchen_Logistics_v2;

import jp.kitchen.Smart_Kitchen_Logistics_v2.model.FoodItem;
import jp.kitchen.Smart_Kitchen_Logistics_v2.service.AnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KitchenController {

    private final AnalysisService analysisService = new AnalysisService();

    @GetMapping("/hello")
    public String hello() {
        return "本日の営業を開始します";
    }

    @GetMapping("/analysis")
    public String analysis() {
        List<FoodItem> items = List.of(
                new FoodItem("米", 30, 200),
                new FoodItem("鮭", 10, 800),
                new FoodItem("マグロ", 5, 1500)
        );

        double total = items.stream().mapToDouble(FoodItem::getTotalCost).sum();
        double costRate = (total / 2040000) * 100;

        StringBuilder sb = new StringBuilder();
        sb.append("=== Smart Kitchen Logistics 分析レポート ===\n");
        sb.append(String.format("原価率: %.1f%% (目標: 74.5%%)\n", costRate));
        if (costRate > 74.5) {
            sb.append("【警告】目標原価率を超過しています！\n");
        }
        items.forEach(i -> {
            double ratio = (i.getTotalCost() / total) * 100;
            sb.append(String.format("品目: %-10s | 原価: %8.0f円 | 構成比: %5.1f%%\n",
                    i.getName(), i.getTotalCost(), ratio));
        });
        sb.append(String.format("\n総原価実績: %.0f円", total));

        return sb.toString().replace("\n", "<br>");
    }
}