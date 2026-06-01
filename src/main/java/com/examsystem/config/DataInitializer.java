package com.examsystem.config;

import com.examsystem.data.entity.Role;
import com.examsystem.data.enums.RoleType;
import com.examsystem.data.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        for (RoleType roleType : RoleType.values()) {
            roleRepository.findByName(roleType)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName(roleType);
                        return roleRepository.save(role);
                    });
        }
    }
}
