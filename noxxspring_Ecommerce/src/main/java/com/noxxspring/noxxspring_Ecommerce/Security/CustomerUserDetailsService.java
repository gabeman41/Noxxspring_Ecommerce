package com.noxxspring.noxxspring_Ecommerce.Security;

import com.noxxspring.noxxspring_Ecommerce.Exceptions.NotFoundException;
import com.noxxspring.noxxspring_Ecommerce.Modal.User;
import com.noxxspring.noxxspring_Ecommerce.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("user/ email not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
