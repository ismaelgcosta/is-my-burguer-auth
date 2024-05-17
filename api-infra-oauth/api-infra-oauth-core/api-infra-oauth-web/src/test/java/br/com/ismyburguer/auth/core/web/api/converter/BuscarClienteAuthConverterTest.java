package br.com.ismyburguer.auth.core.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.entity.User;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BuscarClienteAuthConverterTest {

    private BuscarClienteAuthConverter buscarClienteAuthConverter;

    @BeforeEach
    public void setUp() {
        buscarClienteAuthConverter = new BuscarClienteAuthConverter();
    }

    @Test
    public void testConvertComInputValido() {
        // Arrange
        Cliente.ClienteId clienteId = new Cliente.ClienteId(UUID.randomUUID());
        Cliente cliente = new Cliente(
                clienteId,
                new Cliente.Nome("John", "Doe"),
                new Cliente.Email("john.doe@example.com"),
                new Cliente.CPF("12345678909")
        );
        Cliente.Username johndoe = new Cliente.Username("johndoe");
        cliente.setUsername(johndoe);

        // Act
        User user = buscarClienteAuthConverter.convert(cliente);

        // Assert
        assertNotNull(user);
        assertEquals(clienteId.getClienteId().toString(), user.getClienteId());
        assertEquals("John", user.getNome());
        assertEquals("Doe", user.getSobrenome());
        assertEquals("12345678909", user.getCpf());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    public void testConvertComInputNulo() {
        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> buscarClienteAuthConverter.convert(null));
    }

}
