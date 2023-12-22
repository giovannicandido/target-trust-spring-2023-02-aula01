package br.com.targettrust.spring.aula.model.error;


import lombok.Value;

/**
 * Retorno de errors para exceções
 *
 * @see br.com.targettrust.spring.aula.controller.GlobalExceptionHandler
 */
@Value
public class ApiError {
    private final String message;
    private final String exceptionMessage;
    private final StackTraceElement[] stackTraceElement;
    private final MessageCode code;

    /**
     * Metodo de fabrica para criar ApiError somente com uma mensagem
     *
     * @param message Mensagem de erro
     * @return Api Error
     */
    public static ApiError of(String message) {
        return new ApiError(message, null, null, MessageCode.NO_CODE);
    }

    public static ApiError of(String message, MessageCode messageCode) {
        return new ApiError(message, null, null, messageCode);
    }

    public static ApiError of(String message, String exceptionMessage) {
        return new ApiError(message, exceptionMessage, null, MessageCode.NO_CODE);
    }

    public static ApiError of(String message, String exceptionMessage, StackTraceElement[] stackTraceElement) {
        return new ApiError(message, exceptionMessage, stackTraceElement, MessageCode.NO_CODE);
    }
}
