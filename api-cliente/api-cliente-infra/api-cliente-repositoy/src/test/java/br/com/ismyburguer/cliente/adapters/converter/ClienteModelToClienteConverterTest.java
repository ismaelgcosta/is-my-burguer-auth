package br.com.ismyburguer.cliente.adapters.converter;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteModelToClienteConverterTest {

    private ClienteModelToClienteConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new ClienteModelToClienteConverter();
    }

    @Test
    public void testarConversaoComTodosOsCamposPreenchidos() {
        // Arrange
        ClienteModel clienteModel = new ClienteModel();
        UUID clienteId = UUID.randomUUID();
        clienteModel.setClienteId(clienteId);
        clienteModel.setNome("John");
        clienteModel.setSobrenome("Doe");
        clienteModel.setEmail("john.doe@example.com");
        clienteModel.setCpf("12345678901");
        clienteModel.setUsername("johndoe");

        // Act
        Cliente cliente = converter.convert(clienteModel);

        // Assert
        assertEquals(clienteId, cliente.getClienteId().getClienteId());
        assertEquals("John", cliente.getNome().getNome());
        assertEquals("Doe", cliente.getNome().getSobrenome());
        assertEquals("john.doe@example.com", cliente.getEmail().getEndereco());
        assertEquals("12345678901", cliente.getCpf().map(Cliente.CPF::getNumero).orElse(null));
        assertEquals("johndoe", cliente.getUsername().map(Cliente.Username::getUsername).orElse(null));
    }

    @Test
    public void testarConversaoComCamposOpcionaisAusentes() {
        // Arrange
        ClienteModel clienteModel = new ClienteModel();
        UUID clienteId = UUID.randomUUID();
        clienteModel.setClienteId(clienteId);
        clienteModel.setNome("Jane");
        clienteModel.setSobrenome("Doe");
        clienteModel.setEmail("jane.doe@example.com");
        clienteModel.setCpf(null);
        clienteModel.setUsername(null);

        // Act
        Cliente cliente = converter.convert(clienteModel);

        // Assert
        assertEquals(clienteId, cliente.getClienteId().getClienteId());
        assertEquals("Jane", cliente.getNome().getNome());
        assertEquals("Doe", cliente.getNome().getSobrenome());
        assertEquals("jane.doe@example.com", cliente.getEmail().getEndereco());
        assertNull(cliente.getCpf().orElse(null));
        assertNull(cliente.getUsername().orElse(null));
    }
}

