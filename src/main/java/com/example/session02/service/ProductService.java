package com.example.session02.service;

import com.example.session02.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    boolean insertProduct(Product product);
    boolean updateProduct(Product product, Long productId);
    boolean deleteProduct(Long productId);
    Product getProductById(Long productId);
    List<Product> getProductsByProducer(String producer);
    List<Product> getProductsByProducerAndYearMaking(String producer, Integer yearMaking);
}
