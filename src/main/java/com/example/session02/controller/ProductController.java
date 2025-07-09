package com.example.session02.controller;

import com.example.session02.model.entity.Product;
import com.example.session02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/index_product";
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create_product";
    }

    @GetMapping("/edit")
    public String editProductForm(Long productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/edit_product";
        } else {
            model.addAttribute("error", "Product not found");
            return "error";
        }
    }

    @GetMapping("/delete")
    public String deleteProduct(Long productId, Model model) {
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            model.addAttribute("message", "Product deleted successfully");
        } else {
            model.addAttribute("error", "Product not found or could not be deleted");
        }
        return "redirect:/products";
    }
}
