package com.noxxspring.noxxspring_Ecommerce.Service.impl;

import com.noxxspring.noxxspring_Ecommerce.Dto.LoginRequest;
import com.noxxspring.noxxspring_Ecommerce.Dto.Response;
import com.noxxspring.noxxspring_Ecommerce.Dto.UserDto;
import com.noxxspring.noxxspring_Ecommerce.Enums.UserRole;
import com.noxxspring.noxxspring_Ecommerce.Exceptions.InvalidCredentialException;
import com.noxxspring.noxxspring_Ecommerce.Exceptions.NotFoundException;
import com.noxxspring.noxxspring_Ecommerce.Mapper.ModelDtoMapper;
import com.noxxspring.noxxspring_Ecommerce.Modal.User;
import com.noxxspring.noxxspring_Ecommerce.Repository.UserRepo;
import com.noxxspring.noxxspring_Ecommerce.Security.JwtUtil;
import com.noxxspring.noxxspring_Ecommerce.Service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelDtoMapper modelDtoMapper;

    @Override
    public Response registerUser(UserDto registrationRequest) {
        UserRole role = UserRole.USER;

        if(registrationRequest.getRole() != null && registrationRequest.getRole()
                .equalsIgnoreCase("admin")){
            role= UserRole.ADMIN;
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .build();

        // save returned user
        User savedUser = userRepo.save(user);

        // map the returned user
        UserDto userDto = modelDtoMapper.mapUserToDtoBasic(savedUser);
        return Response.builder()
                .status(200)
                .message("user successfully registered")
                .user(userDto)
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Email not found"));

        // check for password
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("password does not match");
        }

        // generate token for the user
        String token = jwtUtil.generateToken(user);
        return Response.builder()
                .status(200)
                .message("User successfully logged in")
                .expirationTime("6 Months")
                .role(user.getRole().name())
                .build();
    }

    @Override
    public Response getAllUser() {
        List<User> users = userRepo.findAll();
        // map users
        List<UserDto> userDtos = users.stream()
                .map(modelDtoMapper::mapUserToDtoBasic)
                .collect(Collectors.toList());
        return Response.builder()
                .status(200)
                .message("successful")
                .userList(userDtos)
                .build();
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        log.info("user Email is "+ email);
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        User user = getLoginUser();
        UserDto userDto = modelDtoMapper.mapUserToDtoPlusAddressAndOrderHistory(user);
        return Response.builder()
                .status(200)
                .user(userDto)
                .build();
    }
}
