package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;
import java.util.*;
import java.time.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthBuyerDto {

    private Long id;
    private String name;
    private String gender;
    private String email;
    private String mobNo;
    private LocalDateTime dateOfBirth;
    private LocalDateTime createdAt;
    private Set<AddressDto> addresses;
    private Set<OrderDto> orders;
}