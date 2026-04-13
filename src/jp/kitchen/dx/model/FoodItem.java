package jp.kitchen.dx.model;

public class FoodItem {
    private final String name;
    private final double stock;
    private final double price;

    public FoodItem(String name, double stock, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("食材名は必須です");
        }
        if (stock < 0) throw new IllegalArgumentException("在庫量は0以上");
        if (price < 0) throw new IllegalArgumentException("単価は0以上");

        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName()        { return name; }
    public double getStock()       { return stock; }
    public double getPrice()       { return price; }
    public double getTotalCost()   { return stock * price; }
}