package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;
import java.util.*;
import java.time.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String stateId;
    private String address;
    private Long qty;
    private Float totalPrice;
    private Float discountApplied;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Set<ProductDto> products;

}