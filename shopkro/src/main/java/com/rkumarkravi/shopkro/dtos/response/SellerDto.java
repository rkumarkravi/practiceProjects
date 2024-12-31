package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;
import java.util.*;
import java.time.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    private Long id;
    private String sellerName;
    private String sellerAddress;
    private String sellerGst;
    private String email;

}