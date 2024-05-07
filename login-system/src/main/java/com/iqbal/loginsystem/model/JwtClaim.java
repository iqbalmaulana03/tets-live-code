package com.iqbal.loginsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class JwtClaim {

    private Long userId;

    private List<String> roles;
}
