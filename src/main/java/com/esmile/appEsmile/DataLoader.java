package com.esmile.appEsmile;

import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IAppUserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataLoader(IAppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        AppUser admin = AppUser.builder()
                .name("admin")
                .username("admin")
                .email("admin@email.com")
                .password(bCryptPasswordEncoder.encode("admin"))
                .userRoles(UserRoles.ROLE_ADMIN)
                .build();

        AppUser dentist = AppUser.builder()
                .name("dentist")
                .username("dentist")
                .email("dentist@email.com")
                .password(bCryptPasswordEncoder.encode("dentist"))
                .userRoles(UserRoles.ROLE_DENTIST)
                .build();

        AppUser patient = AppUser.builder()
                .name("patient")
                .username("patient")
                .email("patient@email.com")
                .password(bCryptPasswordEncoder.encode("patient"))
                .userRoles(UserRoles.ROLE_PATIENT)
                .build();

        userRepository.save(admin);
        userRepository.save(dentist);
        userRepository.save(patient);

    }
}
