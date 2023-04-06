package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Entities.Category;
import com.example.pi_ease.DAO.Repositories.CategoryRepository;
import com.example.pi_ease.Service.Interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category edit(Category category) {
         return categoryRepository.save(category);
    }

    @Override
    public List<Category> selectAll() {
         return categoryRepository.findAll();
    }

    @Override
    public Category selectByID(int idC) {
        return categoryRepository.findById(idC).get();
    }

    @Override
    public void deleteById(int idC) {
        categoryRepository.deleteById(idC);

    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> addAll(List<Category> listCat) {
        return categoryRepository.saveAll(listCat);
    }

    @Override
    public void deleteAll(List<Category> listCat) {
        categoryRepository.deleteAll(listCat);
    }
}
