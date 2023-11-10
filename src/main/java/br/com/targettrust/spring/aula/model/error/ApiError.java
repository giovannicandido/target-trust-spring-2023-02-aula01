package br.com.targettrust.spring.aula.model.error;


/**
 * Retorno de errors para exceções
 * @see br.com.targettrust.spring.aula.controller.GlobalExceptionHandler
 */
public class ApiError {
    private final String message;
    private final String exceptionMessage;
    private final StackTraceElement[] stackTraceElement;

    public ApiError(String message, String exceptionMessage, StackTraceElement[] stackTraceElement) {
        this.message = message;
        this.exceptionMessage = exceptionMessage;
        this.stackTraceElement = stackTraceElement;
    }

    /**
     * Metodo de fabrica para criar ApiError somente com uma mensagem
     * @param message Mensagem de erro
     * @return Api Error
     */
    public static ApiError of(String message) {
        return new ApiError(message, null, null);
    }

    public static ApiError of(String message, String exceptionMessage) {
        return new ApiError(message, exceptionMessage, null);
    }

    public static ApiError of(String message, String exceptionMessage, StackTraceElement[] stackTraceElement) {
        return new ApiError(message, exceptionMessage, stackTraceElement);
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public StackTraceElement[] getStackTraceElement() {
        return stackTraceElement;
    }
}
