package com.ecom.clients_service.config.initializer;

import com.ecom.clients_service.entity.Role;
import com.ecom.clients_service.entity.User;
import com.ecom.clients_service.repository.RoleRepository;
import com.ecom.clients_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userDao, RoleRepository roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role adminRole;
        Role clientRole;

        var roleOpt = roleDao.findByName("ROLE_ADMIN");
        var roleCl=roleDao.findByName("ROLE_CLIENT");
        if (roleOpt.isPresent()) {
            adminRole = roleOpt.get();
        } else {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole = roleDao.save(adminRole);
        }

        if (userDao.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            userDao.save(admin);
        }

        if(roleDao.findByName("ROLE_CLIENT").isEmpty()){
            clientRole=new Role();
            clientRole.setName("ROLE_CLIENT");
            roleDao.save(clientRole);
        }
    }
}
