package br.com.ismyburguer.cliente.web.api.converter;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.Endereco;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.web.api.response.SolicitacaoExclusaoResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolicitarExclusaoResponseConverterTest {

    @Test
    void convert_ShouldReturnSolicitacaoExclusaoResponse() {
        // Arrange
        SolicitacaoExclusao solicitacaoExclusao = new SolicitacaoExclusao(
                new SolicitacaoExclusao.Nome("Nome Teste"),
                new SolicitacaoExclusao.Telefone("123456789"),
                new Endereco(
                        "Rua Teste",
                        "123",
                        "Complemento Teste",
                        "Bairro Teste",
                        "Cidade Teste",
                        Estado.SP,
                        "12345-678"
                ),
                new Cliente.CPF("123.456.789-09")
        );

        SolicitarExclusaoResponseConverter converter = new SolicitarExclusaoResponseConverter();

        // Act
        SolicitacaoExclusaoResponse result = converter.convert(solicitacaoExclusao);

        // Assert
        assertNotNull(result);
        assertEquals("Nome Teste", result.getNome());
        assertEquals("123456789", result.getTelefone());
        assertEquals("123.***.**9-09", result.getCpf());
        assertEquals("Rua Teste", result.getRua());
        assertEquals("123", result.getNumero());
        assertEquals("Complemento Teste", result.getComplemento());
        assertEquals("Bairro Teste", result.getBairro());
        assertEquals("Cidade Teste", result.getCidade());
        assertEquals(Estado.SP, result.getEstado());
        assertEquals("12***-678", result.getCep());
    }
}
