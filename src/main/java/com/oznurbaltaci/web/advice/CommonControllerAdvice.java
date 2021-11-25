package com.oznurbaltaci.web.advice;


import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ResponseBody
@ControllerAdvice
public class CommonControllerAdvice {
    @Value("${spring.application.name}")
    private String serviceName;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(Objects::nonNull)
                .filter(fieldError -> StringUtils.isNotEmpty(fieldError.getField()))
                .map(f -> f.getField().concat(" ").concat("boş olamaz!"))
                .collect(Collectors.joining());

        return ErrorResponse.builder().serviceName(serviceName).message(message).code(ErrorCode.ARGUMENT_NOT_VALID.getCode()).build();
    }

    @ExceptionHandler(InnovanceServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCommonServiceException(InnovanceServiceException ex) {
        return ErrorResponse.builder().serviceName(serviceName).message(ex.getMessage()).code(ex.getCode()).serviceName(serviceName).extras(ex.getExtras()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex, HttpServletRequest httpServletRequest) {
        log.error(httpServletRequest.getServletPath(), ex);
        return ErrorResponse.builder().serviceName(serviceName).message(ErrorCode.GENERAL_EXCEPTION.getMessage()).code(ErrorCode.ARGUMENT_NOT_VALID.getCode()).build();
    }

}
