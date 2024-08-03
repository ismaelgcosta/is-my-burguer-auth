package br.com.ismyburguer.cliente.web.api.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolicitacaoExclusaoResponseTest {

    @Test
    void getCpf_ReturnsMaskedCpf() {
        // Arrange
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        response.setCpf("123.456.789-00");

        // Act
        String maskedCpf = response.getCpf();

        // Assert
        assertEquals("123.***.**9-00", maskedCpf);
    }

    @Test
    void getTelefone_ReturnsMaskedTelefone() {
        // Arrange
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        response.setTelefone("12-3456789");

        // Act
        String maskedTelefone = response.getTelefone();

        // Assert
        assertEquals("12-***6789", maskedTelefone);
    }

    @Test
    void getCep_ReturnsMaskedCep() {
        // Arrange
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        response.setCep("12-345678");

        // Act
        String maskedCep = response.getCep();

        // Assert
        assertEquals("12-***678", maskedCep);
    }

    @Test
    void getCpf_WithInvalidCpfFormat_ReturnsOriginalCpf() {
        // Arrange
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        response.setCpf("12345678900"); // invalid format

        // Act
        String maskedCpf = response.getCpf();

        // Assert
        assertEquals("12345678900", maskedCpf);
    }

    @Test
    void getTelefone_WithInvalidTelefoneFormat_ReturnsOriginalTelefone() {
        // Arrange
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        response.setTelefone("123456789"); // invalid format

        // Act
        String maskedTelefone = response.getTelefone();

        // Assert
        assertEquals("123456789", maskedTelefone);
    }

    @Test
    void getCep_WithInvalidCepFormat_ReturnsOriginalCep() {
        // Arrange
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        response.setCep("12345678"); // invalid format

        // Act
        String maskedCep = response.getCep();

        // Assert
        assertEquals("12345678", maskedCep);
    }
}
