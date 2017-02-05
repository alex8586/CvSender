package com.alex.service;

import com.alex.domain.User;

import java.util.List;

public interface EmailUserService {

    User create(User user);

    void update(User user);

    void  delete(User user);

    List<User> getAll();
}
