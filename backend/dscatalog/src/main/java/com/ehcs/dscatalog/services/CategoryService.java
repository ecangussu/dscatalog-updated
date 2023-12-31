package com.ehcs.dscatalog.services;

import com.ehcs.dscatalog.DTO.CategoryDTO;
import com.ehcs.dscatalog.entities.Category;
import com.ehcs.dscatalog.repositories.CategoryRepository;
import com.ehcs.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        List<CategoryDTO> categoriesDTO = categories.stream().map(categorie -> new CategoryDTO(categorie)).toList();
        return categoriesDTO;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return new CategoryDTO(category.orElseThrow(() -> new ResourceNotFoundException("Resource Not found")));
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

}
