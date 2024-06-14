package com.mygroup.storeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygroup.storeManagement.Entity.Purchase;

public interface PurchaseRepository extends JpaRepository <Purchase, Long> {
	
}
