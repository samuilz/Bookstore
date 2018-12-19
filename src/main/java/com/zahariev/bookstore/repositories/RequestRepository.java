package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Request;

import java.util.List;

public interface RequestRepository {
    Request save(Request request);

    List<Request> getAll();

    void delete(Request request);

    Request getById(Integer id);
}
