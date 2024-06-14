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
import com.mygroup.storeManagement.Entity.Product;
import com.mygroup.storeManagement.Entity.Purchase;
import com.mygroup.storeManagement.Repository.CouponRepository;
import com.mygroup.storeManagement.Repository.ProductRepository;
import com.mygroup.storeManagement.Repository.PurchaseRepository;

@RestController
@RequestMapping("purchases")
public class PurchaseController {
	
	@Autowired
	PurchaseRepository purchaseRep;
	
	@Autowired
	ProductRepository productRep;
	
	@Autowired
	CouponRepository couponRep;
	
	@GetMapping
	public List<Purchase> getPurchases() {
		return purchaseRep.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Purchase> getPurchase(@PathVariable long id) {
		return purchaseRep.findById(id);
	}
	
	@PostMapping("create")
	public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
		return ResponseEntity.ok(purchaseRep.save(purchase));
	}
	
	@PostMapping("{purchaseId}/addProduct/{productId}/{buyCount}")
	public ResponseEntity<String> addProductToPurchase(
	@PathVariable Long purchaseId, @PathVariable Long productId, @PathVariable Long buyCount) {
		Purchase purchase = purchaseRep.findById(purchaseId).get();
		Product product = productRep.findById(productId).get();
		
		if (purchase == null) return ResponseEntity.ok("Purchase with the given id does not exist.");
		if (product == null) return ResponseEntity.ok("Product with the given id does not exist.");
		if (buyCount <= 0) return ResponseEntity.ok("Incorrect buy amount.");
		if (product.getCurrentStock() < buyCount) return ResponseEntity.ok("Curent product stock isn't enough for the request.");
		
		product.setCurrentStock(product.getCurrentStock() - buyCount);
		
		for (int i = 0; i < buyCount; i++) {
			purchase.getProducts().add(product);
		}
		
		purchaseRep.flush();
		productRep.flush();
		
		return ResponseEntity.ok(buyCount +" "+ product.getName() +" have been successfully added to the purchase.");
	}

	@PostMapping("{purchaseId}/addCoupon/{couponId}")
	public ResponseEntity<String> addCouponToPurchase(
	@PathVariable Long purchaseId, @PathVariable Long couponId) {
		Purchase purchase = purchaseRep.findById(purchaseId).get();
		Coupon coupon = couponRep.findById(couponId).get();
		
		if (purchase == null) return ResponseEntity.ok("Purchase with the given id does not exist.");
		if (coupon == null) return ResponseEntity.ok("Coupon with the given id does not exist.");
		if (coupon.getPurchase() != null) return ResponseEntity.ok("Specified coupon have already been used before.");
		if (purchase.getCoupon() != null) return ResponseEntity.ok("Specified purchase already has a coupon applied to it.");

		coupon.setPurchase(purchase);
		purchaseRep.flush();
		couponRep.flush();
		
		return ResponseEntity.ok("Coupon have been successfully added to the purchase.");
	}
	
	@PostMapping("{id}/complete")
	public ResponseEntity<String> completePurchase(@PathVariable Long id) {
		Purchase purchase = purchaseRep.findById(id).get();
		
		if (purchase == null) return ResponseEntity.ok("Purchase with the given id does not exist.");
		if (purchase.isCompleted()) return ResponseEntity.ok("This purchase have already been completed before.");
		if (purchase.getProducts().size() == 0) return ResponseEntity.ok("No items have been found in the shopping basket.");
		
		Long totalCostDollars = (long) 0;
		for (Product p : purchase.getProducts()) {
			totalCostDollars += p.getCostDollars();
		}

		if (purchase.getCoupon() != null) {
			if (totalCostDollars < purchase.getCoupon().getDiscountDollars())
				return ResponseEntity.ok("The coupon discount can not be larger than the total cost of items.");
			
			totalCostDollars -= purchase.getCoupon().getDiscountDollars();
		}
		
		purchase.setCompleted(true);
		purchaseRep.flush();
		
		return ResponseEntity.ok("The purchase have been successfully completed for "+ totalCostDollars +" dollars.");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePurchase(@PathVariable Long id) {
		purchaseRep.deleteById(id);
		return ResponseEntity.ok("Entry deleted.");
	}

	@DeleteMapping("{purchaseId}/removeProduct/{productId}/{removeCount}")
	public ResponseEntity<String> removeProductFromPurchase(
	@PathVariable Long purchaseId, @PathVariable Long productId, @PathVariable Long removeCount) {
		Purchase purchase = purchaseRep.findById(purchaseId).get();
		Product product = productRep.findById(productId).get();
		
		if (purchase == null) return ResponseEntity.ok("Purchase with the given id does not exist.");
		if (product == null) return ResponseEntity.ok("Product with the given id does not exist.");
		if (removeCount <= 0) return ResponseEntity.ok("Incorrect remove amount.");
		
		Long sameProductCount = (long) 0;
		for (Product p : purchase.getProducts()) {
			if (p.equals(product)) sameProductCount++;
		}
		if (sameProductCount < removeCount)
			return ResponseEntity.ok("Specified remove amount exceeds the amount of products of the same type in the given purchase.");
		
		product.setCurrentStock(product.getCurrentStock() + removeCount);
		
		for (int i = 0; i < removeCount; i++) {
			purchase.getProducts().remove(product);
		}
		
		purchaseRep.flush();
		productRep.flush();
		
		return ResponseEntity.ok(removeCount +" "+ product.getName() +" have been successfully removed from the purchase.");
	}

	@PostMapping("{purchaseId}/removeCoupon")
	public ResponseEntity<String> removeCouponFromPurchase(@PathVariable Long purchaseId) {
		Purchase purchase = purchaseRep.findById(purchaseId).get();
		if (purchase == null) return ResponseEntity.ok("Purchase with the given id does not exist.");

		Coupon coupon = purchase.getCoupon();
		if (coupon == null) return ResponseEntity.ok("Given purchase doesn't have a coupon applied to it.");
		
		coupon.setPurchase(null);
		
		purchaseRep.flush();
		couponRep.flush();
		
		return ResponseEntity.ok("Coupon have been successfully removed from the purchase.");
	}
	
}
