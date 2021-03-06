package com.shoppingmall.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
 *it has responseCode,statusCode and reponseStatus,timestamp and message,status variables
 */
public class ResponseObject
{
    private String responseCode;
    private String statusCode;
    private String reponseStatus;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String status;
}