package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellerDto {

    private Long id;
    private String sellerName;
    private String sellerAddress;
    private String sellerGst;
    private String email;

}