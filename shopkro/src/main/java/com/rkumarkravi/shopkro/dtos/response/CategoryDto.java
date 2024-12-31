package com.rkumarkravi.shopkro.dtos.response;

import lombok.*;
import java.util.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long categoryId;
    private String name;

}