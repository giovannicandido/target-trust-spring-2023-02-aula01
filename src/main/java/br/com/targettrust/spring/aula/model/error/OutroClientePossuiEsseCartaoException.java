package br.com.targettrust.spring.aula.model.error;

public class OutroClientePossuiEsseCartaoException extends DomainException {
    private static final String MESSAGE = "Outra cliente já possui o cartão com mesmo numero";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
