package br.com.targettrust.spring.aula.model.error;

public class EnderecoNaoLocalizadoException extends DomainException {

    private static final String MESSAGE = "Endereço de cep %s não foi localizado";

    private final String cep;

    public EnderecoNaoLocalizadoException(String cep) {
        this.cep = cep;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(cep);
    }
}
