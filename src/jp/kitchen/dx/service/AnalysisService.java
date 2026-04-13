package jp.kitchen.dx.service;

import jp.kitchen.dx.model.FoodItem;
import java.util.List;

public class AnalysisService {

    public void run(List<FoodItem> items, double salesAmount) {

        if (items == null || items.isEmpty()) {
            System.out.println("▲ 食材データがありません。本日の仕込みはゼロです。");
            return;
        }

        System.out.println("=== Smart Kitchen Logistics 分析レポート ===");

        double total = items.stream().mapToDouble(FoodItem::getTotalCost).sum();

        double costRate = (total / salesAmount) * 100;
        System.out.printf("原価率: %.1f%% (目標: 74.5%%)%n", costRate);
        if (costRate > 74.5) {
            System.out.println("[警告] 目標原価率を超過しています！");
        }

        items.forEach(i -> {
            double ratio = (i.getTotalCost() / total) * 100;
            System.out.printf("品目: %-10s | 原価: %8.0f円 | 構成比: %5.1f%%%n",
                    i.getName(), i.getTotalCost(), ratio);
        });

        System.out.printf("%n総原価実績: %.0f円%n", total);
    }
}