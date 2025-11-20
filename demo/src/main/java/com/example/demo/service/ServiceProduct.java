package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.RepositoryProduct;

@Service
public class ServiceProduct {
    private final RepositoryProduct repositoryProduct;

    public ServiceProduct(RepositoryProduct repositoryProduct){
        this.repositoryProduct = repositoryProduct;
    }

    public List<Product> getAllProducts(){
        return repositoryProduct.findAll();
    }

    public Product createProduct(Product product){
        return repositoryProduct.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Product existingProduct = repositoryProduct.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

          // Atualiza os campos
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        //outros campos

        return repositoryProduct.save(existingProduct);
    }

    public void deleteProduct(Long id){
         if (!repositoryProduct.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        repositoryProduct.deleteById(id);
    }
}
