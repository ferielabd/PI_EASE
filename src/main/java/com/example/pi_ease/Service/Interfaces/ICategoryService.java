package com.example.pi_ease.Service.Interfaces;

import com.example.pi_ease.DAO.Entities.Category;

import java.util.List;

public interface ICategoryService {
    Category add(Category category);
    Category edit(Category category);
    List<Category> selectAll();
    Category selectByID(int idC);
    void deleteById(int idC);
    void delete(Category category);
    List<Category> addAll(List<Category>listCat);
    void deleteAll(List<Category> listCat);
}
