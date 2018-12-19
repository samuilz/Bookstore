package com.zahariev.bookstore.services;

import com.zahariev.bookstore.models.Request;

import java.util.List;

public interface RequestService {
    Request save(Request request);

    List<Request> getAll();

    void delete(Request request);

    Request getById(Integer id);
}
