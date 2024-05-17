package br.com.ismyburguer.cliente.web.api.converter;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.request.AlterarClienteRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AlterarClienteRequestConverterTest {

    @Test
    void converter_SolicitacaoAlterarClienteValida_RetornaCliente() {
        // Arrange
        AlterarClienteRequest request = new AlterarClienteRequest();
        request.setNome("John");
        request.setSobrenome("Doe");

        AlterarClienteRequestConverter converter = new AlterarClienteRequestConverter();

        // Act
        Cliente cliente = converter.convert(request);

        // Assert
        assertNotNull(cliente);
        assertEquals("John", cliente.getNome().getNome());
        assertEquals("Doe", cliente.getNome().getSobrenome());
    }

    @Test
    void converter_SolicitacaoNula_LancaConstraintViolationException() {
        // Arrange
        AlterarClienteRequest request = null;
        AlterarClienteRequestConverter converter = new AlterarClienteRequestConverter();

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> converter.convert(request));
    }

    @Test
    void converter_SolicitacaoInvalida_LançaConstraintViolationException() {
        // Arrange
        AlterarClienteRequest request = new AlterarClienteRequest();
        request.setSobrenome("Doe");

        AlterarClienteRequestConverter converter = new AlterarClienteRequestConverter();

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> converter.convert(request));
    }
}
