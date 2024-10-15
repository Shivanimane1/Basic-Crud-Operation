package com.productmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
