package com.rkumarkravi.shopkro.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Object profileInfo;
    private String type;

    public LoginResponse(String token) {
        this.token = token;
    }
}
