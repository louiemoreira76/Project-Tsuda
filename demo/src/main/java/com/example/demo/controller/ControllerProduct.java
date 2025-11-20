package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ServiceProduct;

@RestController
@RequestMapping("/product")
public class ControllerProduct {
    private final ServiceProduct serviceProduct;
    
    public ControllerProduct(ServiceProduct serviceProduct){
        this.serviceProduct = serviceProduct;
    }

    @GetMapping
    public List<Product> getAll(){
        return serviceProduct.getAllProducts();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return serviceProduct.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product){
        try {
            Product updateProduct = serviceProduct.updateProduct(id, product);
            return ResponseEntity.ok(updateProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            serviceProduct.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
