package com.mygroup.storeManagement.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Long costDollars;
    Long currentStock;

    @ManyToOne
    Brand brand;
    
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    List<Purchase> purchases;
    
    public Long getId() {
    	return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCostDollars() {
        return costDollars;
    }

    public void setCostDollars(Long costDollars) {
        this.costDollars = costDollars;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
