package com.ecom.clients_service.service.impl.Security;

import com.ecom.clients_service.dto.Security.RegisterDto;
import com.ecom.clients_service.entity.Role;
import com.ecom.clients_service.entity.Security.AuthentificationRequest;
import com.ecom.clients_service.entity.Security.AuthentificationResponse;
import com.ecom.clients_service.entity.User;
import com.ecom.clients_service.repository.RoleRepository;
import com.ecom.clients_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public UserRepository getUserDao() {
        return userDao;
    }

    public RoleRepository getRoleDao() {
        return roleDao;
    }

    public JwtTokenProvider getJwtTokenProvider() {
        return jwtTokenProvider;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public AuthentificationResponse authenticate (AuthentificationRequest request) {
        System.out.println("Authenticating user: " + request.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userDao.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));

        var jwtToken = jwtTokenProvider.generateToken(user);
        var roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        var userId=user.getId();

        return AuthentificationResponse.builder()
                .token(jwtToken)
                .roles(roles)
                .userId(userId)
                .build();
    }

    public String register(RegisterDto registerDto) {

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleDao.findByName("ROLE_CLIENT");
        userRole.ifPresent(roles::add);

        user.setRoles(roles);

        userDao.save(user);

        return "User register successfully";
    }
}
