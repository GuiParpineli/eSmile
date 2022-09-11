package com.esmile.appEsmile.repository;

import com.esmile.appEsmile.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdressRepository extends JpaRepository<Address, Long> {
}
