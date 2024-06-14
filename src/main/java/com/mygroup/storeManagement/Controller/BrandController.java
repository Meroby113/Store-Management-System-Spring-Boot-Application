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

import com.mygroup.storeManagement.Entity.Brand;
import com.mygroup.storeManagement.Entity.Product;
import com.mygroup.storeManagement.Repository.BrandRepository;
import com.mygroup.storeManagement.Repository.ProductRepository;

@RestController
@RequestMapping("brands")
public class BrandController {

    @Autowired
    BrandRepository brandRep;

    @Autowired
    ProductRepository productRep;

    @GetMapping
    public List<Brand> getBrands() {
        return brandRep.findAll();
    }
    
    @GetMapping("{id}")
    public Optional<Brand> getBrand(@PathVariable Long id){
        return brandRep.findById(id);
    }

    @PostMapping("create")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandRep.save(brand));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
        brandRep.deleteById(id);
        return ResponseEntity.ok("Entry deleted.");
    }
    
    @PostMapping("{id}/createProduct")
    public ResponseEntity<Product> createProduct(@PathVariable Long id, @RequestBody Product product) {
    	product.setBrand(brandRep.findById(id).get());
        return ResponseEntity.ok(productRep.save(product));
    }
}
