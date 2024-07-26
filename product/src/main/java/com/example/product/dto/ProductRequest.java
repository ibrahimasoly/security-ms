package com.example.product.dto;

import jakarta.validation.constraints.NotNull;



public record ProductRequest(
     Long productId,
     @NotNull(message="Le nom du produit est obligatoire")
     String name,
     @NotNull(message="Le description du produit est obligatoire")
     String description,
     @NotNull(message="Le price du produit est obligatoire")
     float price,
     @NotNull(message="Le quantity du produit est obligatoire")
     int quantity,
     Long categoryId
) {
    
}
