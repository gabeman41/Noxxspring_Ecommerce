package com.noxxspring.noxxspring_Ecommerce.Specification;

import com.noxxspring.noxxspring_Ecommerce.Enums.OrderStatus;
import com.noxxspring.noxxspring_Ecommerce.Modal.OrderItem;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderItemSpecification {

    //Specification to filter orderItem by status
    public static Specification<OrderItem> hasStatus (OrderStatus status){
        return ((root, query, criteriaBuilder) -> status != null ? criteriaBuilder
                .equal(root.get("status"),status): null);
    }

    // start date and end date
    public static Specification<OrderItem> createdBetween (LocalDateTime startDate, LocalDateTime endDate){
        return ((root, query, criteriaBuilder) -> {
            if(startDate != null && endDate != null){
                return criteriaBuilder.between(root.get("createdAt"),startDate,endDate);
            }else if(startDate != null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"),startDate);
            } else if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"),endDate);

            }else {
                return null;
            }
        });
    }

    // Specification to filter orderItem by item id
public static Specification<OrderItem> hasItemId (Long itemId){
        return ((root, query, criteriaBuilder) ->
                itemId != null ? criteriaBuilder.equal(root.get("id"),itemId): null);
}
}

