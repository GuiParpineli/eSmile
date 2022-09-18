package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
}
