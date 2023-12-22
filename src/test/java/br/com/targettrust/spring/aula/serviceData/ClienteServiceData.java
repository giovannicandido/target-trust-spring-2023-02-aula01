package br.com.targettrust.spring.aula.serviceData;

import br.com.targettrust.spring.aula.infraestructure.repository.cliente.ClienteRepository;
import br.com.targettrust.spring.aula.model.cliente.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceData {
    private final ClienteRepository clienteRepository;


    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }
}
