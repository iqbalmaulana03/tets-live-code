package com.iqbal.loginsystem.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {

    private String message;

    private String status;
}
