package br.com.ismyburguer.cliente.adapters.converter;
import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteToClienteModelConverterTest {

    private ClienteToClienteModelConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new ClienteToClienteModelConverter();
    }

    @Test
    public void testarConversaoComTodosOsCamposPreenchidos() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        Cliente cliente = new Cliente(
                new Cliente.ClienteId(clienteId),
                new Cliente.Nome("John", "Doe"),
                new Cliente.Email("john.doe@example.com"),
                new Cliente.CPF("12345678901"),
                new Cliente.Username("johndoe")
        );

        // Act
        ClienteModel clienteModel = converter.convert(cliente);

        // Assert
        assertEquals("John", clienteModel.getNome());
        assertEquals("Doe", clienteModel.getSobrenome());
        assertEquals("john.doe@example.com", clienteModel.getEmail());
        assertEquals("12345678901", clienteModel.getCpf().orElse(null));
        assertEquals("johndoe", clienteModel.getUsername());
    }

    @Test
    public void testarConversaoComCamposOpcionaisAusentes() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        Cliente cliente = new Cliente(
                new Cliente.ClienteId(clienteId),
                new Cliente.Nome("Jane", "Doe"),
                new Cliente.Email("jane.doe@example.com"),
                null,
                null
        );

        // Act
        ClienteModel clienteModel = converter.convert(cliente);

        // Assert
        assertEquals("Jane", clienteModel.getNome());
        assertEquals("Doe", clienteModel.getSobrenome());
        assertEquals("jane.doe@example.com", clienteModel.getEmail());
        assertNull(clienteModel.getCpf().orElse(null));
        assertNull(clienteModel.getUsername());
    }
}
