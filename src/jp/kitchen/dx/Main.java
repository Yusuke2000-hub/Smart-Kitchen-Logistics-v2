package jp.kitchen.dx;

import jp.kitchen.dx.model.FoodItem;
import jp.kitchen.dx.service.AnalysisService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<FoodItem> inventory = new ArrayList<>();
        inventory.add(new FoodItem("厳選和牛", 12.0, 5800));
        inventory.add(new FoodItem("季節の地野菜", 95.0, 420));

        AnalysisService service = new AnalysisService();

        // 空リストテスト
        List<FoodItem> emptyList = new ArrayList<>();
        service.run(emptyList, 147000);

        System.out.println("---通常の在庫リスト---");
        service.run(inventory, 147000);

        System.out.println("\n--- [AI異動後のビジョン] ---");
        System.out.println("・将来予測AIによる仕入れ最適化の実現");
        System.out.println("・多言語音声による現場作業ナビゲートの実装");
    }
}