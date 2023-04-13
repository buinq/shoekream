package com.shoekream.domain.cart;

import com.shoekream.domain.BaseTimeEntity;
import com.shoekream.domain.cart.dto.WishProductResponse;
import com.shoekream.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


    public WishProductResponse toWishProductResponse(){
        return WishProductResponse.builder()
                .id(this.id)
                .productId(this.product.getId())
                .productName(this.product.getName())
                .brand(this.product.getBrand())
                .build();
    }

}
