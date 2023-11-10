package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.model.error.ApiError;
import br.com.targettrust.spring.aula.model.error.DomainException;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Um controller advice vai interceptar errors no spring e tratá-los conforme os methodos que tipos de exceções que definirmos
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Tratamento de exceções para tipos NotFoundException
     * @param ex NotFoundException que sera interceptada pelo spring
     * @return Retorna Uma resposta para o cliente que chamou, no caso de api rest é quem executou a API
     */
    @ExceptionHandler(NotFoundException.class) // Aqui dizemos que o metodo trata exceções
    @ResponseStatus(HttpStatus.NOT_FOUND) // Aqui dizemos qual o codigo de retorno para quem chamou a API REST, no caso 404.
    @ResponseBody // Transforma o corpo para ser retornado (no caso é uma string mas poderia ser um objeto que viraria um json)
    public String notFoundException(NotFoundException ex) {
        logger.info(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError domainException(DomainException ex) {
        logger.error(ex.getMessage(), ex);
        return new ApiError(ex.getMessage(), ex.getMessage(), ex.getStackTrace());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError runtimeException(RuntimeException ex) {
        logger.error(ex.getMessage(), ex);
        return ApiError.of("Ocorreu um erro desconhecido tente novamente mais tarde", ex.getMessage());
    }
}
