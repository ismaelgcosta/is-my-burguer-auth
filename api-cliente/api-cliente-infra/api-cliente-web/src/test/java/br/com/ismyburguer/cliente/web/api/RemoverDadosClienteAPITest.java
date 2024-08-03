package br.com.ismyburguer.cliente.web.api;

import static org.junit.jupiter.api.Assertions.*;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarSolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.RemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.CriarSolicitacaoRemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.entity.SolicitacaoExclusao;
import br.com.ismyburguer.cliente.web.api.converter.SolicitarExclusaoConverter;
import br.com.ismyburguer.cliente.web.api.converter.SolicitarExclusaoResponseConverter;
import br.com.ismyburguer.cliente.web.api.request.SolicitacaoExclusaoRequest;
import br.com.ismyburguer.cliente.web.api.response.SolicitacaoExclusaoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverDadosClienteAPITest {

    @Mock
    private CriarSolicitacaoRemocaoDadosClienteUseCase criarSolicitacaoRemocaoDadosClienteUseCase;

    @Mock
    private ConsultarSolicitacaoRemocaoDadosClienteUseCase consultarSolicitacaoRemocaoDadosClienteUseCase;

    @Mock
    private RemocaoDadosClienteUseCase remocaoDadosClienteUseCase;

    @Mock
    private SolicitarExclusaoResponseConverter solicitarExclusaoResponseConverter;

    @Mock
    private SolicitarExclusaoConverter solicitarExclusaoConverter;

    @InjectMocks
    private RemoverDadosClienteAPI removerDadosClienteAPI;

    @Test
    void solicitarExclusaoDeCadastro_DeveCriarSolicitacao() {
        // Arrange
        SolicitacaoExclusaoRequest request = new SolicitacaoExclusaoRequest();
        when(solicitarExclusaoConverter.convert(request)).thenReturn(new SolicitacaoExclusao());

        // Act
        removerDadosClienteAPI.solicitarExclusaoDeCadastro(request);

        // Assert
        verify(criarSolicitacaoRemocaoDadosClienteUseCase, times(1)).solicitar(any(SolicitacaoExclusao.class));
    }

    @Test
    void consultarSolicitarExclusaoDeCadastro_DeveRetornarListaDeSolicitacoes() {
        // Arrange
        String cpf = "123.456.789-00";
        SolicitacaoExclusao solicitacaoExclusao = new SolicitacaoExclusao();
        SolicitacaoExclusaoResponse response = new SolicitacaoExclusaoResponse();
        when(consultarSolicitacaoRemocaoDadosClienteUseCase.consultar(cpf)).thenReturn(List.of(solicitacaoExclusao));
        when(solicitarExclusaoResponseConverter.convert(solicitacaoExclusao)).thenReturn(response);

        // Act
        List<SolicitacaoExclusaoResponse> result = removerDadosClienteAPI.consultarSolicitarExclusaoDeCadastro(cpf);

        // Assert
        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
    }

    @Test
    void exclusaoDeCadastro_DeveRemoverDadosDoCliente() {
        // Arrange
        String cpf = "123.456.789-00";

        // Act
        removerDadosClienteAPI.exclusaoDeCadastro(cpf);

        // Assert
        verify(remocaoDadosClienteUseCase, times(1)).remover(any(ConsultarClienteUseCase.ConsultaClientePorCpf.class));
    }
}
