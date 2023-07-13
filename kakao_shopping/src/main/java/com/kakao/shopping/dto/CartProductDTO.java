package com.kakao.shopping.dto;

import java.util.List;

public record CartProductDTO(
        int id,
        String productName,
        List<CartItemDTO> carts
) {
}