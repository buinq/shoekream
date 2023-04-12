package com.shoekream.domain.product;

import com.shoekream.domain.BaseTimeEntity;
import com.shoekream.domain.brand.Brand;
import com.shoekream.domain.product.common.Currency;
import com.shoekream.domain.product.common.SizeClassification;
import com.shoekream.domain.product.common.SizeUnit;
import com.shoekream.domain.product.dto.*;
import com.shoekream.domain.trade.Trade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String modelNumber;

    private String color;

    private LocalDate releaseDate;

    private Long releasePrice;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private SizeClassification sizeClassification;

    @Enumerated(EnumType.STRING)
    private SizeUnit sizeUnit;

    private Double minSize;

    private Double maxSize;

    private Double sizeGap;

    private String originImagePath;

    private String thumbnailImagePath;
    private String resizedImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trade> trades = new ArrayList<>();

    public static Product createProduct(ProductCreateRequest request, Brand savedBrand) {
        return Product.builder()
                .name(request.getName())
                .modelNumber(request.getModelNumber())
                .color(request.getColor())
                .releaseDate(request.getReleaseDate())
                .releasePrice(request.getReleasePrice())
                .currency(request.getCurrency())
                .sizeClassification(request.getSizeClassification())
                .sizeUnit(request.getSizeUnit())
                .minSize(request.getMinSize())
                .maxSize(request.getMaxSize())
                .sizeGap(request.getSizeGap())
                .originImagePath(request.getOriginImagePath())
                .thumbnailImagePath(request.getThumbnailImagePath())
                .resizedImagePath(request.getResizedImagePath())
                .brand(savedBrand)
                .build();
    }

    public ProductCreateResponse toProductCreateResponse() {
        return ProductCreateResponse.builder()
                .name(this.name)
                .modelNumber(this.modelNumber)
                .minSize(this.minSize)
                .maxSize(this.maxSize)
                .brandName(this.brand.getName())
                .build();
    }

    public ProductInfo toProductInfo() {
        return ProductInfo.builder()
                .id(this.id)
                .name(this.name)
                .modelNumber(this.modelNumber)
                .color(this.color)
                .releaseDate(this.releaseDate)
                .releasePrice(this.releasePrice)
                .currency(this.currency)
                .sizeClassification(this.sizeClassification)
                .sizeUnit(this.sizeUnit)
                .minSize(this.minSize)
                .maxSize(this.maxSize)
                .sizeGap(this.sizeGap)
                .brandInfo(this.brand.toBrandInfo())
                .originImagePath(this.originImagePath)
                .thumbnailImagePath(this.thumbnailImagePath)
                .resizedImagePath(this.resizedImagePath)
                .build();
    }

    public ProductDeleteResponse toProductDeleteResponse() {
        return ProductDeleteResponse.builder()
                .name(this.name)
                .modelNumber(this.modelNumber)
                .brandName(this.brand.getName())
                .build();
    }

    public void update(ProductUpdateRequest updatedProduct) {
        this.name = updatedProduct.getName();
        this.modelNumber = updatedProduct.getModelNumber();
        this.color = updatedProduct.getColor();
        this.releaseDate = updatedProduct.getReleaseDate();
        this.releasePrice = updatedProduct.getReleasePrice();
        this.currency = updatedProduct.getCurrency();
        this.sizeClassification = updatedProduct.getSizeClassification();
        this.sizeUnit = updatedProduct.getSizeUnit();
        this.minSize = updatedProduct.getMinSize();
        this.maxSize = updatedProduct.getMaxSize();
        this.sizeGap = updatedProduct.getSizeGap();
        this.originImagePath = updatedProduct.getOriginImagePath();
        this.thumbnailImagePath = updatedProduct.getThumbnailImagePath();
        this.resizedImagePath = updatedProduct.getResizedImagePath();
    }

    public ProductUpdateResponse toProductUpdateResponse() {
        return ProductUpdateResponse.builder()
                .name(this.name)
                .modelNumber(this.modelNumber)
                .minSize(this.minSize)
                .maxSize(this.maxSize)
                .brandName(this.brand.getName())
                .originImagePath(this.originImagePath)
                .build();
    }
}
