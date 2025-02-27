package com.example.fragment.models;

import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private int price;
    private int imageResId;

    // Constructor không có id, tự động tạo id duy nhất
    public Product(String name, String description, int price, int imageResId) {
        this.id = UUID.randomUUID().toString(); // Tạo id duy nhất
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Constructor có id (nếu bạn muốn đặt id cụ thể)
    public Product(String id, String name, String description, int price, int imageResId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getter cho id
    public String getId() {
        return id;
    }

    // Các getter khác
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
