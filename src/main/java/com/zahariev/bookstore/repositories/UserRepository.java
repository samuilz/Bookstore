package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.User;

public interface UserRepository {
    User save(User user);
}
