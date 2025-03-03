package com.microservice.catalog.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class MessageDto {

    private HttpStatus status;
    private String message;

}
