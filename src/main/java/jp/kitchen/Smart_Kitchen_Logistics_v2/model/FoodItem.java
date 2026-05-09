package jp.kitchen.Smart_Kitchen_Logistics_v2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "food_items")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double stock;

    @Column(nullable = false)
    private double price;

    // JPA用コンストラクタ（必須）
    protected FoodItem() {}

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

    public Long getId()            { return id; }
    public String getName()        { return name; }
    public double getStock()       { return stock; }
    public double getPrice()       { return price; }
    public double getTotalCost()   { return stock * price; }
}