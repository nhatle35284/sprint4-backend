package com.example.sprint4.service;

import com.example.sprint4.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

//    List<User> findAllByStatusTrue();

    Role findById(Long id);

    void save(Role role);

    void deleteById(Long id);

}
