package br.com.targettrust.spring.aula.model.error;

public class OutraPessoaPossuiEsseCartaoException extends DomainException {
    private static final String MESSAGE = "Outra pessoa já possui o cartão com mesmo numero";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
