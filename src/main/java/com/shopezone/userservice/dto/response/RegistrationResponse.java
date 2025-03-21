package com.shopezone.userservice.dto.response;

import com.shopezone.userservice.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponse {
    private User user;
    private String jwtToken;
}
