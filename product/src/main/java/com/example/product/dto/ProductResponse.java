package com.example.product.dto;

import java.util.List;

public record ProductResponse(
     String name,
     String description,
     float price,
     int quantity,
     Long categoryId,
     List<ImageResponse> images
) {
    
}
