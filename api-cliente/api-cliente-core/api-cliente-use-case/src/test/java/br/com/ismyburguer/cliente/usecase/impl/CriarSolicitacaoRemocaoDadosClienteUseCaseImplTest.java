package br.com.ismyburguer.cliente.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;

import br.com.caelum.stella.type.Estado;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.entity.Endereco;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.gateway.out.CriarSolicitacaoExclusaoRepository;
import br.com.ismyburguer.cliente.usecase.impl.CriarSolicitacaoRemocaoDadosClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarSolicitacaoRemocaoDadosClienteUseCaseImplTest {

    @Mock
    private CriarSolicitacaoExclusaoRepository criarSolicitacaoExclusaoRepository;

    @InjectMocks
    private CriarSolicitacaoRemocaoDadosClienteUseCaseImpl criarSolicitacaoRemocaoDadosClienteUseCase;

    @Test
    @Transactional
    void solicitar_DeveSalvarSolicitacaoComSucesso() {
        // Arrange
        SolicitacaoExclusao solicitacaoExclusao = new SolicitacaoExclusao(
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

        // Act
        criarSolicitacaoRemocaoDadosClienteUseCase.solicitar(solicitacaoExclusao);

        // Assert
        verify(criarSolicitacaoExclusaoRepository, times(1)).salvarSolicitacao(solicitacaoExclusao);
    }
}
