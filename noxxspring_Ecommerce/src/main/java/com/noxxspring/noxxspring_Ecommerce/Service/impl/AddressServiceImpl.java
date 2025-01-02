package com.noxxspring.noxxspring_Ecommerce.Service.impl;

import com.noxxspring.noxxspring_Ecommerce.Dto.AddressDto;
import com.noxxspring.noxxspring_Ecommerce.Dto.Response;
import com.noxxspring.noxxspring_Ecommerce.Modal.Address;
import com.noxxspring.noxxspring_Ecommerce.Modal.User;
import com.noxxspring.noxxspring_Ecommerce.Repository.AddressRepo;
import com.noxxspring.noxxspring_Ecommerce.Service.interf.AddressService;
import com.noxxspring.noxxspring_Ecommerce.Service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;
    private UserService userService;

    @Override
    public Response saveAndUpdateAddress(AddressDto addressDto) {
        User user = userService.getLoginUser();
        Address address = user.getAddress();

        if(address == null){
            address = new Address();
            address.setUser(user);
        }
        if (addressDto.getStreet()!= null) address.setStreet(addressDto.getStreet());
        if (addressDto.getCity()!= null) address.setCity(addressDto.getCity());
        if (addressDto.getState()!= null) address.setState(addressDto.getState());
        if (addressDto.getZipCode()!= null) address.setZipCode(addressDto.getZipCode());
        if (addressDto.getCountry()!= null) address.setCountry(addressDto.getCountry());
        addressRepo.save(address);

        // build message
        String message = (user.getAddress() == null) ? "Address Successfully created" :
                "Address Successfully updated";

        return Response.builder()
                .status(200)
                .message(message)
                .build();
    }
}
