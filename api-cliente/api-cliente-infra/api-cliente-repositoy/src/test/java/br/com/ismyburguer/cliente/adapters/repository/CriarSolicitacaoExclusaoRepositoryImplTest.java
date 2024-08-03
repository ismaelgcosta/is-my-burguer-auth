package br.com.ismyburguer.cliente.adapters.repository;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.adapters.converter.SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarSolicitacaoExclusaoRepositoryImplTest {

    @Mock
    private SolicitacaoExclusaoRepository repository;

    @Mock
    private SolicitacaoExclusaoToSolicitacaoExclusaoModelConverter converter;

    @InjectMocks
    private CriarSolicitacaoExclusaoRepositoryImpl criarSolicitacaoExclusaoRepository;

    private SolicitacaoExclusao solicitacaoExclusao;
    private SolicitacaoExclusaoModel solicitacaoExclusaoModel;
    private UUID solicitacaoExclusaoId;

    @BeforeEach
    void setUp() {
        solicitacaoExclusaoId = UUID.randomUUID();
        solicitacaoExclusao = new SolicitacaoExclusao(
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
        );
        solicitacaoExclusaoModel = new SolicitacaoExclusaoModel(
                solicitacaoExclusaoId,
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
        );
    }

    @Test
    void salvarSolicitacao_DeveSalvarESolicitarComSucesso() {
        // Arrange
        when(converter.convert(solicitacaoExclusao)).thenReturn(solicitacaoExclusaoModel);
        when(repository.save(solicitacaoExclusaoModel)).thenReturn(solicitacaoExclusaoModel);

        // Act
        UUID result = criarSolicitacaoExclusaoRepository.salvarSolicitacao(solicitacaoExclusao);

        // Assert
        assertEquals(solicitacaoExclusaoId, result);
        verify(converter, times(1)).convert(solicitacaoExclusao);
        verify(repository, times(1)).save(solicitacaoExclusaoModel);
    }
}
