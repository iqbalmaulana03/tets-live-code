package com.iqbal.loginsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserResponse {

    private Long id;

    private String fullName;
}
