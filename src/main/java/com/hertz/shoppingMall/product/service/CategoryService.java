package com.hertz.shoppingMall.product.service;

import com.hertz.shoppingMall.product.model.Category;
import com.hertz.shoppingMall.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoryListAll(){
        return categoryRepository.findAll();
    }

    @Transactional  // 변경감지(dirty checking)
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional  // 변경감지(dirty checking)
    public void updateCategory(Long id, String name) {
        Category findCategory = categoryRepository.findById(id).orElse(null);
        if(findCategory != null){
            findCategory.setName(name);
        }
    }
}
