package com.mygroup.storeManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygroup.storeManagement.Entity.Coupon;
import com.mygroup.storeManagement.Repository.CouponRepository;

@RestController
@RequestMapping("coupons")
public class CouponController {
	
	@Autowired
	CouponRepository couponRep;
	
	@GetMapping
	public List<Coupon> getCoupons() {
		return couponRep.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Coupon> getCoupon(@PathVariable Long id) {
		return couponRep.findById(id);
	}
	
	@PostMapping("create")
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
		return ResponseEntity.ok(couponRep.save(coupon));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
		couponRep.deleteById(id);
		return ResponseEntity.ok("Successfully deleted.");
	}
}
