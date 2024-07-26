package com.example.product.dto;

public record ImageResponse(
    Long imageId,
    String filename,
    Long productId
) {
    
}
