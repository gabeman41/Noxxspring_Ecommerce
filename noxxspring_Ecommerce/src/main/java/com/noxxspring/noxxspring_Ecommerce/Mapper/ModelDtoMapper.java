package com.noxxspring.noxxspring_Ecommerce.Mapper;

import com.noxxspring.noxxspring_Ecommerce.Dto.*;
import com.noxxspring.noxxspring_Ecommerce.Modal.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ModelDtoMapper {

    //map user entity to user Dto
    public UserDto mapUserToDtoBasic (User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        userDto.setName(user.getName());
        return userDto;
    }

    //map Address to Dto Basic
    public AddressDto mapAddressToDtoBasic(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setStreet(address.getStreet());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    // map category to Dto Basic
    public CategoryDto mapCategoryToDtoBasic (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    // map OrderItem to Dto Basic
    public OrderItemDto mapOrderItemToDtoBasic (OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setStatus(orderItem.getStatus().name());
        orderItemDto.setCreatedAt(orderItem.getCreatedAt());
        return orderItemDto;
    }

    // map product to Dto Basic
    public ProductDto mapProductToDtoBasic (Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }

    // add address to the user
    public  UserDto mapUserDtoPlusAddress(User user){
        UserDto userDto = mapUserToDtoBasic(user);
        if(user.getAddress() != null){
            AddressDto addressDto = mapAddressToDtoBasic(user.getAddress());
            userDto.setAddress(addressDto);
        }
        return userDto;
    }

    //adding product to orderitem
    public OrderItemDto mapOrderItemToDtoPlusProduct (OrderItem orderItem){
        OrderItemDto orderItemDto = mapOrderItemToDtoBasic(orderItem);
        if(orderItem.getProduct() != null){
            ProductDto productDto = mapProductToDtoBasic(orderItem.getProduct());
            orderItemDto.setProduct(productDto);
        }
        return orderItemDto;
    }

    // orderItem to Dto plus product and user
    public OrderItemDto mapOrderItemToDtoPlusProductAndUser (OrderItem orderItem){
        OrderItemDto orderItemDto = mapOrderItemToDtoPlusProduct(orderItem);
        if(orderItem.getUser() != null){
            UserDto userDto = mapUserDtoPlusAddress(orderItem.getUser());
            orderItemDto.setUser(userDto);
        }
        return orderItemDto;
    }

    // user to Dto with address and orderHistory
    public UserDto mapUserToDtoPlusAddressAndOrderHistory (User user){
        UserDto userDto = mapUserDtoPlusAddress(user);
        if(user.getOrderItemList() != null && !user.getOrderItemList().isEmpty()){
            userDto.setOrderItemList(user.getOrderItemList()
                    .stream()
                    .map(this::mapOrderItemToDtoPlusProduct)
                    .collect(Collectors.toList()));
        }
        return userDto;
    }
}
