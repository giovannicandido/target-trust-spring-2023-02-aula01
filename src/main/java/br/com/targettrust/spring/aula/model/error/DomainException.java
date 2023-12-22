package br.com.targettrust.spring.aula.model.error;

/**
 * É comum temos uma classe que representa execeções da nosso aplicação e subclasses concretas com essas exeções
 * Usar RuntimeException é muito generico e representa qualquer erro, que seja ou não da aplicação
 */
public abstract class DomainException extends RuntimeException {
}
