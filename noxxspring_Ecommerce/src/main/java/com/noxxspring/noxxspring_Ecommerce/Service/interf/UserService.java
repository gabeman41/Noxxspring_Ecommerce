package com.noxxspring.noxxspring_Ecommerce.Service.interf;

import com.noxxspring.noxxspring_Ecommerce.Dto.LoginRequest;
import com.noxxspring.noxxspring_Ecommerce.Dto.Response;
import com.noxxspring.noxxspring_Ecommerce.Dto.UserDto;
import com.noxxspring.noxxspring_Ecommerce.Modal.User;

public interface UserService {
    Response registerUser (UserDto registrationRequest);

    Response loginUser (LoginRequest loginRequest);

    Response getAllUser();

    User getLoginUser();

    Response getUserInfoAndOrderHistory();
}
