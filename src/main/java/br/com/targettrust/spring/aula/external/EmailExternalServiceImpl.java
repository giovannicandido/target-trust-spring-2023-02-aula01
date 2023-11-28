package br.com.targettrust.spring.aula.external;

import br.com.targettrust.spring.aula.service.EmailExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailExternalServiceImpl implements EmailExternalService {
    @Override
    public void enviarEmail(String destinatario, String mensagem, String assunto) {
        log.info("Enviando email para " + destinatario + " com assunto " + assunto);
    }
}
