package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;

import java.time.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private String buyerName;
    private String mobileNo;
    private String locality;
    private String area;
    private String city;
    private String state;
    private String pinCode;
    private String landMark;
    private String alternateMobNo;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}