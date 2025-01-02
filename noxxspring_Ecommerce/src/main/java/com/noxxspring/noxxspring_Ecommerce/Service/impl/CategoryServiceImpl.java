package com.noxxspring.noxxspring_Ecommerce.Service.impl;

import com.noxxspring.noxxspring_Ecommerce.Dto.CategoryDto;
import com.noxxspring.noxxspring_Ecommerce.Dto.Response;
import com.noxxspring.noxxspring_Ecommerce.Exceptions.NotFoundException;
import com.noxxspring.noxxspring_Ecommerce.Mapper.ModelDtoMapper;
import com.noxxspring.noxxspring_Ecommerce.Modal.Category;
import com.noxxspring.noxxspring_Ecommerce.Repository.CategoryRepo;
import com.noxxspring.noxxspring_Ecommerce.Service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelDtoMapper modelDtoMapper;

    @Override
    public Response createCategory(CategoryDto categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("category created successfully")
                .build();
    }

    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
         Category category = categoryRepo.findById(categoryId)
                 .orElseThrow(() -> new NotFoundException("category Not Found"));
         category.setName(categoryRequest.getName());
         categoryRepo.save(category);
        return Response.builder()
                .status(200)
                .message("category updated successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(modelDtoMapper::mapCategoryToDtoBasic)
                .collect(Collectors.toList());
        return Response.builder()
                .status(200)
                .categoryList(categoryDtos)
                .build();
    }

    @Override
    public Response getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("category not found"));
        CategoryDto categoryDto = modelDtoMapper.mapCategoryToDtoBasic(category);
        return Response.builder()
                .status(200)
                .category(categoryDto)
                .build();
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("category no found"));
        categoryRepo.delete(category);
        return Response.builder()
                .status(200)
                .message("category deleted successfully")
                .build();
    }
}
