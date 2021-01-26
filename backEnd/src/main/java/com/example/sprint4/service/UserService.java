package com.example.sprint4.service;

import com.example.sprint4.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

//    List<User> findAllByStatusTrue();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    void changePassWord(Long id, String password);
    void existsByUsername(String username);

//    List<User> searchUser(String inputSearch);
}
