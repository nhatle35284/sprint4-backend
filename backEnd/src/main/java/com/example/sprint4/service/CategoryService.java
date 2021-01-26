package com.example.sprint4.service;

import com.example.sprint4.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

//    List<Category> findAllByStatusTrue();

    Category findById(Long id);

    void save(Category category);

    void deleteById(Long id);
}
