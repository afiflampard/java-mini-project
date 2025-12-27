package com.gdsc.boilerplate.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String fullname;
    private String email;
    private String password;
    private String address;
    private String identityNumber;
    private String phoneNumber;
    private String role;
}
