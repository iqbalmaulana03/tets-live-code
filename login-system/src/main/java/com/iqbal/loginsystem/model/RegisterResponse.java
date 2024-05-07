package com.iqbal.loginsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class RegisterResponse {

    private Long userId;

    private String username;
}
