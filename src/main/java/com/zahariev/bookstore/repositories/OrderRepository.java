package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Order;

public interface OrderRepository {
    Order save(Order order);
}
