package br.com.targettrust.spring.aula.service.external;

public interface EmailExternalService {
    void enviarEmail(String destinatario, String mensagem, String assunto);
}
