package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;

import java.time.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Long minOrderQtyAllowed;
    private Long maxOrderQtyAllowed;
    private Long qty;
    private String description;
    private Float price;
    private Float discount;
    private LocalDate mfdDate;
    private LocalDate expDate;
    private CategoryDto category;
    private String sku;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private SellerDto seller;

}