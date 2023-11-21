package br.com.targettrust.spring.aula.model.error;

import lombok.Value;

@Value
public class NotFoundException extends DomainException {
    private final String message = "O registro do item %s de id %s não foi encontrado";
    private final String item;
    private final String id;

    public NotFoundException(String item, String id) {
        this.item = item;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message.formatted(item, id);
    }
}
