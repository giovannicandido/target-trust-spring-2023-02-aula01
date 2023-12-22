package br.com.targettrust.spring.aula.model.error;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageCode {
    REGISTRY_NOT_FOUND(MessageCategory.BUSINESS),

    CREDIT_CARD_ALREADY_USED(MessageCategory.BUSINESS),
    NO_CODE(MessageCategory.BUSINESS);

    private final MessageCategory category;
}
