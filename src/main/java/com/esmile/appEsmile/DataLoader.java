package com.esmile.appEsmile;

import com.esmile.appEsmile.entity.Address;
import com.esmile.appEsmile.entity.AppUser;
import com.esmile.appEsmile.login.UserRoles;
import com.esmile.appEsmile.repository.IAdressRepository;
import com.esmile.appEsmile.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IAppUserRepository userRepository;
    private final IAdressRepository adressRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataLoader(IAppUserRepository userRepository, IAdressRepository adressRepository ) {
        this.userRepository = userRepository;
        this.adressRepository = adressRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        AppUser admin = AppUser.builder()
                .username("admin")
                .email("admin@email.com")
                .password(bCryptPasswordEncoder.encode("admin"))
                .userRoles(UserRoles.ROLE_ADMIN)
                .build();

        AppUser dentist = AppUser.builder()
                .username("dentist")
                .email("dentist@email.com")
                .password(bCryptPasswordEncoder.encode("dentist"))
                .userRoles(UserRoles.ROLE_DENTIST)
                .build();

        AppUser patient = AppUser.builder()
                .username("patient")
                .email("patient@email.com")
                .password(bCryptPasswordEncoder.encode("patient"))
                .userRoles(UserRoles.ROLE_PATIENT)
                .build();

        Address address = Address.builder()
                .street("Dos bobos")
                .number("0")
                .neighborhood("Jardim Diamantes")
                .zipcode("23939-000")
                .city("Diamantina")
                .state("Pedras")
                .build();

        userRepository.save(admin);
        userRepository.save(dentist);
        userRepository.save(patient);
        adressRepository.save(address);

    }
}
