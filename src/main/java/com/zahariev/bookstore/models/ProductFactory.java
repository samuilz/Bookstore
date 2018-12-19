package com.zahariev.bookstore.models;

public class ProductFactory {

    public Product getInstance(String productType) {
        Product product = null;
        switch (productType) {
            case "Book":
                product = new Book();
                break;
            case "BoardGame":
                product = new BoardGame();
                break;
            case "Puzzle":
                product = new Puzzle();
                break;
        }

        return product;
    }
}
