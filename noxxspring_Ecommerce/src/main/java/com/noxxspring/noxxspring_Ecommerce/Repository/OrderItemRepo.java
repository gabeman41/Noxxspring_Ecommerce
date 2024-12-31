package com.noxxspring.noxxspring_Ecommerce.Repository;

import com.noxxspring.noxxspring_Ecommerce.Modal.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long>, JpaSpecificationExecutor<OrderItem> {
}
