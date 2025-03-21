package com.shopezone.userservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String tokenType = "Bearer";
    private String username;
    private String roles;

    public LoginResponse(String token, String username, String roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public LoginResponse(String token) {
        this.token = token;
    }
}
