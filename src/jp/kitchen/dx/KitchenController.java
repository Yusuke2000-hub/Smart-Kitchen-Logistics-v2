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
        analysisService.run(items, 2040000);
        return "分析完了！コンソールを確認してください";
    }

}