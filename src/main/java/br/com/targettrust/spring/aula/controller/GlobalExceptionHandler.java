package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.model.error.ApiError;
import br.com.targettrust.spring.aula.model.error.DomainException;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError domainException(DomainException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError runtimeException(RuntimeException ex) {
        return new ApiError("Ocorreu um erro desconhecido tente novamente mais tarde");
    }
}
