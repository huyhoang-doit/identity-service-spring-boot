package com.hdi.identity_service.configuration;

import com.hdi.identity_service.entity.User;
import com.hdi.identity_service.enums.Role;
import com.hdi.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
           if( userRepository.findByUsername("admin").isEmpty()) {
               var roles = new HashSet<String>();
               roles.add(Role.ADMIN.name());
               User user = User.builder()
                       .username("admin")
//                       .roles(roles)
                       .password(passwordEncoder.encode("admin"))
                       .build();

               userRepository.save(user);
               log.warn("admin user has been created with default password: admin, please change it!");
           }
        };
    }
}
