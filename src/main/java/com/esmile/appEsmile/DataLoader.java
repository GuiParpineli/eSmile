package com.esmile.appEsmile;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IAppUserRepository userRepository;

    @Autowired
    public DataLoader(IAppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.save(new AppUser("Admin", "admin", "admin@teste.com", "teste",
                UserRoles.ROLE_ADMIN));
        userRepository.save(new AppUser("Patient", "patient", "patient@teste.com", "teste",
                UserRoles.ROLE_PATIENT));
        userRepository.save(new AppUser("Dentist", "dentist", "dentist@teste.com", "teste",
                UserRoles.ROLE_DENTIST));

    }
}