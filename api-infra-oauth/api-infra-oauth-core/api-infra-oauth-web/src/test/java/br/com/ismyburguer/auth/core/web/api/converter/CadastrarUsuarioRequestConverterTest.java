package br.com.ismyburguer.auth.core.web.api.converter;

import br.com.ismyburguer.auth.core.web.api.request.UserSignUpRequest;
import br.com.ismyburguer.cliente.entity.Cliente;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CadastrarUsuarioRequestConverterTest {

    private CadastrarUsuarioRequestConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new CadastrarUsuarioRequestConverter();
    }

    @Test
    public void testarConversaoComEntradaValida() {
        // Arrange
        UserSignUpRequest request = new UserSignUpRequest();
        request.setNome("John Doe");
        request.setEmail("john.doe@example.com");
        request.setCpf("12345678909");
        request.setPassword("password");

        // Act
        Cliente cliente = converter.convert(request);

        // Assert
        assertNotNull(cliente);
        assertEquals("John Doe", cliente.getNome().getNome());
        assertEquals("john.doe@example.com", cliente.getEmail().getEndereco());
        assertTrue(cliente.getCpf().isPresent());
        assertEquals("12345678909", cliente.getCpf().get().getNumero());
    }

    @Test
    public void testarConversaoComEntradaNula() {
        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> converter.convert(null));
    }

}
