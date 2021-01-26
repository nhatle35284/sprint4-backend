package com.example.sprint4.service.impl;

import com.example.sprint4.model.User;
import com.example.sprint4.repository.UserRepository;
import com.example.sprint4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changePassWord(Long id, String password) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
        }
    }

    @Override
    public void existsByUsername(String username) {
        userRepository.existsByUsername(username);
    }

//    @Override
//    public List<User> searchUser(String inputSearch) {
//        return userRepository.f;
//    }
}
