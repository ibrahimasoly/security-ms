package com.example.product.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue
    private Long productId;
    private String name;
    private String description;
    private float price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Categorys category;
    @OneToMany(mappedBy="product")
    private List<Image> images;
}
