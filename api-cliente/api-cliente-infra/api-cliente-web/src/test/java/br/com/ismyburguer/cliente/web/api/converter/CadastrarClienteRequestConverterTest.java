package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.request.CriarClienteRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CadastrarClienteRequestConverterTest {

    @Test
    void converter_CriarClienteRequestValido_RetornaCliente() {
        // Arrange
        CriarClienteRequest request = new CriarClienteRequest();
        request.setNome("John");
        request.setSobrenome("Doe");
        request.setEmail("john.doe@example.com");

        CadastrarClienteRequestConverter converter = new CadastrarClienteRequestConverter();

        // Act
        Cliente cliente = converter.convert(request);

        // Assert
        assertNotNull(cliente);
        assertEquals("John", cliente.getNome().getNome());
        assertEquals("Doe", cliente.getNome().getSobrenome());
        assertEquals("john.doe@example.com", cliente.getEmail().getEndereco());
        assertFalse(cliente.getCpf().isPresent());
    }

    @Test
    void converter_RequestNulo_LancaConstraintViolationException() {
        // Arrange
        CriarClienteRequest request = null;
        CadastrarClienteRequestConverter converter = new CadastrarClienteRequestConverter();

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> converter.convert(request));
    }

}
