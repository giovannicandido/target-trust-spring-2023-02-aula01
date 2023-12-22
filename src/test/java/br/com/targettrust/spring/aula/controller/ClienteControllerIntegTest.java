package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.serviceData.ClienteServiceData;
import br.com.targettrust.spring.aula.stub.ClienteStub;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
class ClienteControllerIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteServiceData clienteServiceData;

    @Test
    void listAllClientes() throws Exception {
        ClienteStub.createClientes(3).forEach(cliente -> clienteServiceData.save(cliente));

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*]", Matchers.hasSize(3)));

    }

}