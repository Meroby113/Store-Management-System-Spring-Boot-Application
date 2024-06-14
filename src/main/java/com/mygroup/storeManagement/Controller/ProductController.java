package com.mygroup.storeManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygroup.storeManagement.Entity.Product;
import com.mygroup.storeManagement.Repository.ProductRepository;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	ProductRepository productRep;
	
	@GetMapping
	public List<Product> getProducts() {
		return productRep.findAll();
	}
    
    @GetMapping("{id}")
    public Optional<Product> getProduct(@PathVariable Long id){
        return productRep.findById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    	productRep.deleteById(id);
        return ResponseEntity.ok("Entry deleted.");
    }
}
