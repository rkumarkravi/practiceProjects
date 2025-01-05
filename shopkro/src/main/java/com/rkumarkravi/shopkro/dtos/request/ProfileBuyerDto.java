package com.rkumarkravi.shopkro.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.*;
import java.time.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileBuyerDto {
    private String name;
    private String gender;
    private String email;
    private String mobNo;
    private LocalDate dateOfBirth;

}