package com.noxxspring.noxxspring_Ecommerce.Repository;

import com.noxxspring.noxxspring_Ecommerce.Modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
