package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type",
        discriminatorType = DiscriminatorType.STRING)
//@MappedSuperclass
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer productId;
    private String name;
    private Integer stock;
    private Double price;

    public Product() {

    }

    public Product(Integer id, String name, Integer stock, Double price) {
        this.productId = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
