package br.com.ismyburguer.cliente.adapters.converter;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.Endereco;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolicitacaoExclusaoToSolicitacaoExclusaoModelConverterTest {

    @Test
    void convert_ShouldReturnSolicitacaoExclusaoModel() {
        // Arrange
        SolicitacaoExclusao source = new SolicitacaoExclusao(
                new SolicitacaoExclusao.Nome("Nome Teste"),
                new SolicitacaoExclusao.Telefone("123456789"),
                new Endereco("Rua Teste", "123", "Complemento Teste", "Bairro Teste", "Cidade Teste", Estado.SP, "12345-678"),
                new Cliente.CPF("12345678900")
        );

        SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter converter = new SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter();

        // Act
        SolicitacaoExclusaoModel result = converter.convert(source);

        // Assert
        assertNotNull(result);
        assertEquals("123456789", result.getTelefone());
        assertEquals("Nome Teste", result.getNome());
        assertEquals("12345678900", result.getCpf());
        assertEquals("Rua Teste", result.getRua());
        assertEquals("123", result.getNumero());
        assertEquals("Complemento Teste", result.getComplemento());
        assertEquals("Bairro Teste", result.getBairro());
        assertEquals("Cidade Teste", result.getCidade());
        assertEquals(Estado.SP, result.getEstado());
        assertEquals("12345-678", result.getCep());
    }
}
