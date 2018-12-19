package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Order;

public interface OrderService {
    Order save(Order order);
}
