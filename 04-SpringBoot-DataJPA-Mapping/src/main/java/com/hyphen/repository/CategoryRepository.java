package com.hyphen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyphen.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
