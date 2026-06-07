package com.dondoc.service;

import com.dondoc.dto.CategoryResponse;
import com.dondoc.entity.Category;
import com.dondoc.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getCategories() {
        List<Category> entities = categoryRepository.findAll();
        return entities.stream()
                .map(entity -> new CategoryResponse(
                        entity.getId(),
                        entity.getName(),
                        entity.getType()
                ))
                .collect(Collectors.toList());
    }
}
