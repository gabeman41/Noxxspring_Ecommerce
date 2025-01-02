package com.noxxspring.noxxspring_Ecommerce.Service.interf;

import com.noxxspring.noxxspring_Ecommerce.Dto.AddressDto;
import com.noxxspring.noxxspring_Ecommerce.Dto.Response;

public interface AddressService {
    Response saveAndUpdateAddress(AddressDto addressDto);
}
