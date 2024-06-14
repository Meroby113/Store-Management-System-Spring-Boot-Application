package com.mygroup.storeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygroup.storeManagement.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
