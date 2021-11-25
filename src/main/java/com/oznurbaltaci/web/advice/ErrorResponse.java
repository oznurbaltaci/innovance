package com.oznurbaltaci.web.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oznurbaltaci.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Integer code;
    private String message;
    private String serviceName;
    private Object extras;

    public static ErrorResponse setValues(ErrorCode errorCode, String serviceName) {
        return ErrorResponse.builder().serviceName(serviceName).message(errorCode.getMessage()).code(errorCode.getCode()).build();
    }
}
