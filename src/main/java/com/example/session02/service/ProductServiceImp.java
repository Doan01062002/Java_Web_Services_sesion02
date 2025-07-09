package com.example.session02.service;

import com.example.session02.model.entity.Product;
import com.example.session02.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean insertProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product, Long productId) {
        try {
            if (productRepository.existsById(productId)) {
                product.setProductId(productId);
                productRepository.save(product);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long productId) {
        try {
            if (productRepository.existsById(productId)) {
                productRepository.deleteById(productId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getProductsByProducer(String producer) {
        try {
            return productRepository.findProductByProducerContains(producer);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Product> getProductsByProducerAndYearMaking(String producer, Integer yearMaking) {
        try {
            return productRepository.findProductsByProducerContainsAndYearMaking(producer, yearMaking);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }


}
