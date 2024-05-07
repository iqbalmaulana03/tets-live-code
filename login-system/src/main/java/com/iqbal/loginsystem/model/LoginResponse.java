package com.iqbal.loginsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class LoginResponse {

    private String token;

    private List<String> roles;
}
