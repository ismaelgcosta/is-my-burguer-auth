package br.com.ismyburguer.cliente.adapters.converter;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoExclusaoModelToSolicitacaoExclusaoConverterTest {

    @Test
    void convert_ValidSolicitacaoExclusaoModel_ReturnsSolicitacaoExclusao() {
        // Arrange
        SolicitacaoExclusaoModel model = new SolicitacaoExclusaoModel();
        model.setNome("John Doe");
        model.setTelefone("123456789");
        model.setRua("Main St");
        model.setNumero("123");
        model.setComplemento("Apt 4");
        model.setBairro("Downtown");
        model.setCidade("Springfield");
        model.setEstado(Estado.SP);
        model.setCep("12345-678");
        model.setCpf("123.456.789-00");

        SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter converter = new SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter();

        // Act
        SolicitacaoExclusao solicitacaoExclusao = converter.convert(model);

        // Assert
        assertNotNull(solicitacaoExclusao);
        assertEquals("John Doe", solicitacaoExclusao.getNome().getDescricao());
        assertEquals("123456789", solicitacaoExclusao.getTelefone().getTelefone());
        assertEquals("Main St", solicitacaoExclusao.getEndereco().getRua());
        assertEquals("123", solicitacaoExclusao.getEndereco().getNumero());
        assertEquals("Apt 4", solicitacaoExclusao.getEndereco().getComplemento());
        assertEquals("Downtown", solicitacaoExclusao.getEndereco().getBairro());
        assertEquals("Springfield", solicitacaoExclusao.getEndereco().getCidade());
        assertEquals(Estado.SP, solicitacaoExclusao.getEndereco().getEstado());
        assertEquals("12345-678", solicitacaoExclusao.getEndereco().getCep());
        assertEquals("123.456.789-00", solicitacaoExclusao.getCpf().getNumero());
    }

    @Test
    void convert_NullSolicitacaoExclusaoModel_ThrowsNullPointerException() {
        // Arrange
        SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter converter = new SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> converter.convert(null));
    }
}
