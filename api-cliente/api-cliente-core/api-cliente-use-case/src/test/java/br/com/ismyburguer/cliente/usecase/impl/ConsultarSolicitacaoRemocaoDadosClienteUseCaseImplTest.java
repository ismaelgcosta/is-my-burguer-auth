package br.com.ismyburguer.cliente.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.Endereco;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.ConsultarSolicitacaoExclusaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarSolicitacaoRemocaoDadosClienteUseCaseImplTest {

    @Mock
    private ConsultarSolicitacaoExclusaoRepository consultarSolicitacaoExclusaoRepository;

    @InjectMocks
    private ConsultarSolicitacaoRemocaoDadosClienteUseCaseImpl useCase;

    private List<SolicitacaoExclusao> solicitacoes;

    @BeforeEach
    void setUp() {
        // Initialize test data
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
    }

    @Test
    void consultar_RetornaListaDeSolicitacoes() {
        // Arrange
        String cpf = "123456789";
        when(consultarSolicitacaoExclusaoRepository.listarSolicitacoes(cpf)).thenReturn(solicitacoes);

        // Act
        List<SolicitacaoExclusao> result = useCase.consultar(cpf);

        // Assert
        assertEquals(solicitacoes, result);
        verify(consultarSolicitacaoExclusaoRepository, times(1)).listarSolicitacoes(cpf);
    }
}
