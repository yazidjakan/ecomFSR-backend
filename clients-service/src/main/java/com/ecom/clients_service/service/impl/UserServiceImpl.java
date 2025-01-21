package com.ecom.clients_service.service.impl;

import com.ecom.clients_service.dto.RoleDto;
import com.ecom.clients_service.dto.UserDto;
import com.ecom.clients_service.entity.Role;
import com.ecom.clients_service.entity.User;
import com.ecom.clients_service.repository.RoleRepository;
import com.ecom.clients_service.repository.UserRepository;
import com.ecom.clients_service.service.facade.UserService;
import com.ecom.clients_service.transformer.RoleTransformer;
import com.ecom.clients_service.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserTransformer userTransformer;
    private final UserRepository userDao;
    private final RoleTransformer roleTransformer;
    private final RoleRepository roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        log.info("Fetching all users");
        List<User> users=userDao.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return userTransformer.toDto(users);
    }

    private UserDto prepareUser(UserDto dto){
        List<User> usersRegistred=userDao.findAll();
        long nbreAdmins=usersRegistred.stream()
                .map(user -> user.getRoles().equals("ROLE_ADMIN"))
                .count();
        if(nbreAdmins !=0 && dto.roleDtos().equals("ROLE_ADMIN")){
            throw new RuntimeException("Il peut y'avoir qu'un seul Admin");
        }else{
            return dto;
        }
    }
    @Override
    public UserDto save(UserDto userDto) {
        log.info("Attempting to create user with username: {}", userDto.username());
        if (userDto.username() != null) {
            boolean userExists = userDao.findByUsername(userDto.username()).isPresent();
            if (userExists) {
                throw new RuntimeException("This username is already used");
            }
        }
        if (userDto.roleDtos() == null || userDto.roleDtos().isEmpty()) {
            throw new RuntimeException("User must have at least one role");
        }
        try {
            log.info("Roles reçus: {}", userDto.roleDtos()); // Ajoutez ce log pour vérifier les rôles reçus

            User user = userTransformer.toEntity(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Set<Role> userRoles = new HashSet<>();
            for (RoleDto roleDto : userDto.roleDtos()) {
                Optional<Role> roleOptional = roleDao.findByName(roleDto.name());
                if (roleOptional.isPresent()) {
                    userRoles.add(roleOptional.get());
                } else {
                    Role newRole = new Role();
                    newRole.setName(roleDto.name());
                    newRole = roleDao.save(newRole);
                    userRoles.add(newRole);
                }
            }
            user.setRoles(userRoles);
            log.info("User created successfully with username: {}", userDto.username());
            return userTransformer.toDto(userDao.save(user));
        } catch (Exception ex) {
            log.error("Error occurred while creating user with username: {}", userDto.username(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the User." + ex);
        }
    }




    @Override
    public UserDto findById(Long id) {

        log.info("Fetching user by ID: {}", id);
        return userDao.findById(id)
                .map(userTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new RuntimeException("Unable to find a User with the given Id : "+id);
                });
    }

    @Override
    public UserDto findByUsername(String username) {
        log.info("Fetching user by username: {}", username);

        return userDao.findByUsername(username)
                .map(userTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return  new RuntimeException("Unable to find a User with the given username : "+username);
                });
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        log.info("Updating user with ID: {}", id);

        // Vérifie si l'utilisateur existe
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mets à jour les propriétés de l'utilisateur
        existingUser.setUsername(userDto.username());
        existingUser.setPassword(userDto.password());
        existingUser.setEmail(userDto.email());

        // Gère les rôles associés
        existingUser.getRoles().clear();
        existingUser.getRoles().addAll(roleTransformer.toEntitySet(userDto.roleDtos()));

        // Sauvegarde l'utilisateur mis à jour
        User updatedUser = userDao.save(existingUser);

        log.info("User updated successfully with ID: {}", id);
        return userTransformer.toDto(updatedUser);
    }

    @Override
    public void deleteById(Long id) {

        log.info("Deleting user with ID: {}", id);

        UserDto foundUser = findById(id);
        foundUser.roleDtos().clear();
        userDao.deleteById(foundUser.id());
        log.info("User deleted successfully with ID: {}", id);
    }

}
