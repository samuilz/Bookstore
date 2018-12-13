package com.zahariev.bookstore.models;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@MappedSuperclass
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer stock;
    private Double price;

    public Product() {

    }

    public Product(String name, Integer stock, Double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
