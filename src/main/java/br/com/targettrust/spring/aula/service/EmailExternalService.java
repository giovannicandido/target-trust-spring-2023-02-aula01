package br.com.targettrust.spring.aula.service;

public interface EmailExternalService {
    void enviarEmail(String destinatario, String mensagem, String assunto);
}
