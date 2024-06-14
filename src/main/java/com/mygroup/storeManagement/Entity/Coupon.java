package com.mygroup.storeManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long discountDollars;

    @OneToOne
    @JsonIgnore
    Purchase purchase;
    
    public Long getId() {
    	return id;
    }

    public Long getDiscountDollars() {
        return discountDollars;
    }

    public void setDiscountDollars(Long discountDollars) {
        this.discountDollars = discountDollars;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

}
