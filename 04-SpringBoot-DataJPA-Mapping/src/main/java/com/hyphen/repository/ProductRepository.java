package com.hyphen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyphen.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
