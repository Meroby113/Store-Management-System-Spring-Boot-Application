package com.mygroup.storeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygroup.storeManagement.Entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
