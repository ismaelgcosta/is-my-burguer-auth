package br.com.ismyburguer.cliente.web.api.converter;

import static org.junit.jupiter.api.Assertions.*;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.web.api.request.SolicitacaoExclusaoRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolicitarExclusaoConverterTest {

    @Test
    void convert_ShouldReturnSolicitacaoExclusao() {
        // Arrange
        SolicitacaoExclusaoRequest request = new SolicitacaoExclusaoRequest();
        request.setNome("Nome Teste");
        request.setTelefone("123456789");
        request.setRua("Rua Teste");
        request.setNumero("123");
        request.setComplemento("Complemento Teste");
        request.setBairro("Bairro Teste");
        request.setCidade("Cidade Teste");
        request.setEstado(Estado.SP);
        request.setCep("12345-678");
        request.setCpf("12345678909");

        SolicitarExclusaoConverter converter = new SolicitarExclusaoConverter();

        // Act
        SolicitacaoExclusao result = converter.convert(request);

        // Assert
        assertNotNull(result);
        assertEquals("Nome Teste", result.getNome().getDescricao());
        assertEquals("123456789", result.getTelefone().getTelefone());
        assertEquals("Rua Teste", result.getEndereco().getRua());
        assertEquals("123", result.getEndereco().getNumero());
        assertEquals("Complemento Teste", result.getEndereco().getComplemento());
        assertEquals("Bairro Teste", result.getEndereco().getBairro());
        assertEquals("Cidade Teste", result.getEndereco().getCidade());
        assertEquals(Estado.SP, result.getEndereco().getEstado());
        assertEquals("12345-678", result.getEndereco().getCep());
        assertEquals("12345678909", result.getCpf().getNumero());
    }

    @Test
    void convert_ShouldThrowConstraintViolationException_WhenRequestIsNull() {
        // Arrange
        SolicitarExclusaoConverter converter = new SolicitarExclusaoConverter();

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> converter.convert(null));
    }
}
