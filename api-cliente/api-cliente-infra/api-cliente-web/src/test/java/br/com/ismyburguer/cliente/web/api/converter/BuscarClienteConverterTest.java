package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.response.BuscarClienteResponse;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BuscarClienteConverterTest {

    @Test
    void converter_ClienteValido_RetornaBuscarClienteResponse() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("John", "Doe"), new Cliente.Email("john.doe@example.com"), null);
        UUID clienteId = UUID.randomUUID();
        cliente.setClienteId(new Cliente.ClienteId(clienteId));

        BuscarClienteConverter converter = new BuscarClienteConverter();

        // Act
        BuscarClienteResponse response = converter.convert(cliente);

        // Assert
        assertNotNull(response);
        assertEquals(clienteId.toString(), response.getClienteId());
        assertEquals("John", response.getNome());
        assertEquals("Doe", response.getSobrenome());
        assertEquals("jo******@example.com", response.getEmail());
    }

    @Test
    void converter_ClienteNulo_LancaConstraintViolationException() {
        // Arrange
        Cliente cliente = null;
        BuscarClienteConverter converter = new BuscarClienteConverter();

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> converter.convert(cliente));
    }

}
