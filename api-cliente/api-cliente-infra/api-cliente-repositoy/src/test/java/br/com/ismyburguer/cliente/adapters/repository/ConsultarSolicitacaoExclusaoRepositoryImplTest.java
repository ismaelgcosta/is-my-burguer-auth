package br.com.ismyburguer.cliente.adapters.repository;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.adapters.converter.SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter;
import br.com.ismyburguer.cliente.adapters.model.SolicitacaoExclusaoModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.Endereco;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarSolicitacaoExclusaoRepositoryImplTest {

    @Mock
    private SolicitacaoExclusaoRepository solicitacaoExclusaoRepository;

    @Mock
    private SolicitacaoExclusaoModelToSolicitacaoExclusaoConverter converter;

    @InjectMocks
    private ConsultarSolicitacaoExclusaoRepositoryImpl consultarSolicitacaoExclusaoRepository;

    private List<SolicitacaoExclusao> solicitacoes;
    private List<SolicitacaoExclusaoModel> solicitacoesModel;

    @BeforeEach
    void setUp() {
        // Initialize test data
        solicitacoesModel = List.of(new SolicitacaoExclusaoModel(
                UUID.randomUUID(),
                "telefone",
                "nome",
                "12345678909",
                "rua",
                "numero",
                "complemento",
                "bairro",
                "cidade",
                Estado.AP,
                "00000-000"
        ));
        solicitacoes = List.of(new SolicitacaoExclusao(
                new SolicitacaoExclusao.Nome("nome"),
                new SolicitacaoExclusao.Telefone("telefone"),
                new Endereco(
                        "rua",
                        "numero",
                        "complemento",
                        "bairro",
                        "cidade",
                        Estado.AP,
                        "00000-000"
                ),
                new Cliente.CPF("12345678909")
        ));

        // Mock the conversion
        when(converter.convert(any(SolicitacaoExclusaoModel.class))).thenReturn(solicitacoes.get(0));
    }

    @Test
    void listarSolicitacoes_RetornaListaDeSolicitacoes() {
        // Arrange
        String cpf = "123456789";
        when(solicitacaoExclusaoRepository.findAllByCpf(cpf)).thenReturn(solicitacoesModel);

        // Act
        List<SolicitacaoExclusao> result = consultarSolicitacaoExclusaoRepository.listarSolicitacoes(cpf);

        // Assert
        assertEquals(solicitacoes, result);
        verify(solicitacaoExclusaoRepository, times(1)).findAllByCpf(cpf);
        verify(converter, times(1)).convert(solicitacoesModel.get(0));
    }
}
