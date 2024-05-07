package com.iqbal.loginsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

    private String username;

    private String password;
}
