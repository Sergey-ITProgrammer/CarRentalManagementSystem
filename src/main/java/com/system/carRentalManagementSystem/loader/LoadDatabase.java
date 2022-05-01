package com.system.carRentalManagementSystem.loader;

import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

        return args -> {
            userRepository.save(new User("Sergey", "someCoNo", "tsbrbe@email.com", "2000-12-12"));
            userRepository.save(new User("Sergey", "someCoNo", "tsbragrfae@email.com", "2000-04-23"));
            userRepository.save(new User("Sergey", "someCoNo", "sefbrbe@email.com", "2000-05-04"));
        };
    }
}
