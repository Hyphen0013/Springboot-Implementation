package com.hyphen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyphen.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {}
