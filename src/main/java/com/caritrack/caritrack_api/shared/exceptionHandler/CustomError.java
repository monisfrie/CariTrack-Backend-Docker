package com.caritrack.caritrack_api.shared.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CustomError {

    private LocalDateTime timestamp;
    private Integer code;
    private String message;

}
