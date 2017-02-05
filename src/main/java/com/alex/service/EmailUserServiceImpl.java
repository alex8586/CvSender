package com.alex.service;

import com.alex.domain.User;
import com.alex.repositories.EmailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailUserServiceImpl implements EmailUserService {

    @Autowired
    private EmailUserRepository emailUserRepository;

    @Override
    public User create(User user) {
        if(user != null){
            emailUserRepository.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public void update(User user) {
        if (user != null){
            emailUserRepository.saveAndFlush(user);
        }
    }

    @Override
    public void delete(User user) {
        if(user != null) {
            emailUserRepository.delete(user);
        }
    }

    @Override
    public List<User> getAll() {
        return emailUserRepository.findAll();
    }
}
