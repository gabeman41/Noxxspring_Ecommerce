package com.noxxspring.noxxspring_Ecommerce.Repository;

import com.noxxspring.noxxspring_Ecommerce.Modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
