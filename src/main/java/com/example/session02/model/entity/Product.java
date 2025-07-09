package com.example.session02.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long productId;

    @Column(name = "product_name", length = 100)
    public String productName;

    @Column(name = "producer", length = 100)
    public String producer;

    @Column(name = "year_making")
    public Integer yearMaking;

    @Column(name = "expire_date")
    public Date expireDate;

    @Column(name = "price")
    public Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
}