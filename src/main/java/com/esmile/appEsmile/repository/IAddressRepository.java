package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
