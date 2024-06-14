package com.mygroup.storeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygroup.storeManagement.Entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
