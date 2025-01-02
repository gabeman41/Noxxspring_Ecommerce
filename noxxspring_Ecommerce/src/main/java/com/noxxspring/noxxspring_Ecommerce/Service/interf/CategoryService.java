package com.noxxspring.noxxspring_Ecommerce.Service.interf;

import com.noxxspring.noxxspring_Ecommerce.Dto.CategoryDto;
import com.noxxspring.noxxspring_Ecommerce.Dto.Response;

public interface CategoryService {

    Response createCategory (CategoryDto categoryRequest);

    Response updateCategory (Long categoryId, CategoryDto categoryRequest);

    Response getAllCategories();

    Response getCategoryById (Long categoryId);

    Response deleteCategory (Long categoryId);

}